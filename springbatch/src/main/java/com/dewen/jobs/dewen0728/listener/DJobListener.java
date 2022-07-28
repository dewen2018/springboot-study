package com.dewen.jobs.dewen0728.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class DJobListener extends JobExecutionListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(DJobListener.class);

    StopWatch sw = new StopWatch();

    // @Resource
    // private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    // private long startTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.STARTED) {
            sw.start();
            log.info("JOB STARTED");
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        sw.stop();
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("JOB FINISHED");
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            log.info("JOB FAILED");
        }
        log.info("Job Cost Time : {}millis", sw.getTotalTimeMillis());
    }
}