package com.dewen.handler;

import com.alibaba.fastjson.JSON;
import com.dewen.bean.DelayJob;
import com.dewen.bean.Job;
import com.dewen.constants.DelayConfig;
import com.dewen.constants.JobStatus;
import com.dewen.container.DelayBucket;
import com.dewen.container.JobPool;
import com.dewen.container.ReadyQueue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * handler
 */
@Slf4j
@Data
@AllArgsConstructor
public class DelayJobHandler implements Runnable {

    /**
     * 延迟队列
     */
    private DelayBucket delayBucket;
    /**
     * 任务池
     */
    private JobPool jobPool;

    private ReadyQueue readyQueue;
    /**
     * 索引
     */
    private int index;

    /**
     *
     */
    @Override
    public void run() {
        log.info("定时任务开始执行");
        while (true) {
            try {
                DelayJob delayJob = delayBucket.getFirstDelayTime(index);
                //没有任务
                if (delayJob == null) {
                    sleep();
                    continue;
                }
                // 发现延时任务
                // 延迟时间没到
                if (delayJob.getDelayDate() > System.currentTimeMillis()) {
                    sleep();
                    continue;
                }
                Job job = jobPool.getJob(delayJob.getJodId());

                //延迟任务元数据不存在，还需要将桶里边的数据删除，避免重复循环
                if (job == null) {
                    log.info("移除不存在任务:{}", JSON.toJSONString(delayJob));
                    delayBucket.removeDelayTime(index, delayJob);
                    continue;
                }

                JobStatus status = job.getStatus();
                if (JobStatus.RESERVED.equals(status)) {
                    log.info("处理超时任务:{}", JSON.toJSONString(job));
                    // 超时任务，重新放进任务池和桶内
                    processTtrJob(delayJob, job);
                } else {
                    log.info("处理延时任务:{}", JSON.toJSONString(job));
                    // 延时任务
                    processDelayJob(delayJob, job);
                }
            } catch (Exception e) {
                log.error("扫描DelayBucket出错：" + e.getMessage());
                sleep();
            }
        }
    }

    /**
     * 处理ttr的任务(超时)
     */
    private void processTtrJob(DelayJob delayJob, Job job) {
        // 修改任务状态
        job.setStatus(JobStatus.DELAY);
        jobPool.addJob(job);
        // 移除delayBucket中的任务
        delayBucket.removeDelayTime(index, delayJob);
        Long delayDate = System.currentTimeMillis() + job.getDelayTime();
        delayJob.setDelayDate(delayDate);
        // 再次添加到任务中
        delayBucket.addDelayJob(delayJob);
    }

    /**
     * 处理延时任务
     */
    private void processDelayJob(DelayJob delayJob, Job job) {
        // 修改任务池状态
        job.setStatus(JobStatus.READY);
        jobPool.addJob(job);
        // 设置到待处理任务
        readyQueue.pushJob(delayJob);
        // 移除delayBucket中的任务
        delayBucket.removeDelayTime(index, delayJob);
    }

    private void sleep() {
        try {
            Thread.sleep(DelayConfig.SLEEP_TIME);
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }
}