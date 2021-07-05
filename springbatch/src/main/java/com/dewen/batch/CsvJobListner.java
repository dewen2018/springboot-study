package com.dewen.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author wxw
 * @version 2019/1/18
 */
@Component
public class CsvJobListner implements JobExecutionListener {

    long startTime;
    long endTime;
    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        System.out.println("任务处理开始");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        endTime = System.currentTimeMillis();
        System.out.println("任务结束");
        System.out.println("耗时："+(endTime-startTime)+"ms");
    }
}
