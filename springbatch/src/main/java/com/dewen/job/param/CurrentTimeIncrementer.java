package com.dewen.job.param;

import com.dewen.util.DateUtils;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;


public class CurrentTimeIncrementer implements JobParametersIncrementer {

    @Override
    public JobParameters getNext(JobParameters parameters) {
        String today = DateUtils.getCurrentDateTime("yyyy-MM-dd");

        return new JobParametersBuilder().addString("run.date", today)
                .addLong("run.id", System.currentTimeMillis())
                .toJobParameters();
    }

}
