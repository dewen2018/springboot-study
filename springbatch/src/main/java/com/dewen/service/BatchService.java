package com.dewen.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("batchService")
public class BatchService {

    // 框架自动注入
    @Autowired
    private JobLauncher jobLauncher;
    // @Autowired
    // @Qualifier("customerJob")
    // private Job customerJob;

    /**
     * 根据 taskId 创建一个Job
     *
     * @param taskId
     * @throws Exception
     */
    public void createBatchJob(String taskId) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        // JobParameters jobParameters = new JobParametersBuilder()
        //         .addString("taskId", taskId)
        //         .addString("uuid", UUID.randomUUID().toString().replace("-", ""))
        //         .toJobParameters();
        // // 传入一个Job任务和任务需要的参数
        // jobLauncher.run(customerJob, jobParameters);
    }
}