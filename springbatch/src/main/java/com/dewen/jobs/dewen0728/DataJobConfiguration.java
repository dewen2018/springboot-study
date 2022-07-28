package com.dewen.jobs.dewen0728;

import com.dewen.jobs.common.CurrentTimeIncrementer;
import com.dewen.jobs.dewen0728.listener.DJobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

// @Configuration
public class DataJobConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DataJobConfiguration.class);
    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private DJobListener jobListener;

    @Autowired
    @Qualifier("firstStep")
    private Step firstStep;

    // @Autowired
    // @Qualifier("step1")
    // private Step step1;
    //
    // @Autowired
    // @Qualifier("step2")
    // private Step step2;
    //
    // @Autowired
    // @Qualifier("step3")
    // private Step step3;

    /**
     * 一个Job通常由一个或者多个Step组成
     */
    @Bean(name = "customerJob")
    public Job customerJob() {
        return jobBuilderFactory.get("customerJob")
                // 自定义incrementer
                // .incrementer(new RunIdIncrementer())
                .incrementer(new CurrentTimeIncrementer())
                //JobListener
                .listener(jobListener)
                //start是JOB执行的第一个step
                .start(firstStep)
                // .start(step1)
                // .next(step2)
                // .next(step3)

                // 每个step操作返回的不同状态，进行判定是否进入下一个step
                // .start(step1)
                // .on("COMPLETED").to(step2)
                // .from(step2).on("COMPLETED").to(step3)
                // .from(step3).end()
                .build();
    }
}
