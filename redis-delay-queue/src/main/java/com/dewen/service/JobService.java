package com.dewen.service;

import com.dewen.bean.DelayJob;
import com.dewen.bean.Job;
import com.dewen.constants.JobStatus;
import com.dewen.container.DelayBucket;
import com.dewen.container.JobPool;
import com.dewen.container.ReadyQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private DelayBucket delayBucket;

    @Autowired
    private ReadyQueue readyQueue;

    @Autowired
    private JobPool jobPool;


    public DelayJob addDefJob(Job job) {
        job.setStatus(JobStatus.DELAY);
        jobPool.addJob(job);
        DelayJob delayJob = new DelayJob(job);
        delayBucket.addDelayJob(delayJob);
        return delayJob;
    }

    /**
     * 获取
     *
     * @return
     */
    public Job getProcessJob(String topic) {
        // 拿到任务
        DelayJob delayJob = readyQueue.popJob(topic);
        if (delayJob == null || delayJob.getJodId() == 0L) {
            return new Job();
        }
        Job job = jobPool.getJob(delayJob.getJodId());
        // 元数据已经删除，则取下一个
        if (job == null) {
            job = getProcessJob(topic);
        } else {
            job.setStatus(JobStatus.RESERVED);
            delayJob.setDelayDate(System.currentTimeMillis() + job.getTtrTime());

            jobPool.addJob(job);
            delayBucket.addDelayJob(delayJob);
        }
        return job;
    }

    /**
     * 完成一个执行的任务
     *
     * @param jobId
     * @return
     */
    public void finishJob(Long jobId) {
        jobPool.removeDelayJob(jobId);
    }

    /**
     * 完成一个执行的任务
     *
     * @param jobId
     * @return
     */
    public void deleteJob(Long jobId) {
        jobPool.removeDelayJob(jobId);
    }

}
