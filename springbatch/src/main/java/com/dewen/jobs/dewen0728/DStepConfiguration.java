package com.dewen.jobs.dewen0728;

import com.dewen.jobs.dewen0728.listener.DChunkListener;
import com.dewen.jobs.dewen0728.listener.DStepExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DStepConfiguration {
    @Resource
    private StepBuilderFactory stepBuilderFactory;
    @Resource
    private DStepExecutionListener stepListener;
    @Resource
    private DChunkListener chunkListener;

    /**
     * TaskletStep
     * 基础的Step主要分为三个部分
     *
     * @param dReader  读取数据
     * @param dProcess 处理数据
     * @param dWriter  写数据
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean(name = "firstStep")
    public Step firstStep(@Qualifier("dReader") JpaPagingItemReader dReader,
                          @Qualifier("dProcess") ItemProcessor dProcess,
                          @Qualifier("dWriter") ItemWriter dWriter) {
        return stepBuilderFactory.get("firstStep")
                // 监听器
                .listener(stepListener)
                // processor100条后writer一次
                .chunk(5)
                //捕捉到异常就重试,重试100次还是异常,JOB就停止并标志失败
                .faultTolerant().retryLimit(3).retry(Exception.class).skipLimit(100).skip(Exception.class)
                .listener(chunkListener)
                .reader(dReader)
                .processor(dProcess)
                .writer(dWriter)
                .build();
    }


    // @Bean(name = "step1")
    // public Step step1() {
    //     return stepBuilderFactory.get("step1")
    //             .tasklet(new Tasklet() {
    //                 @Override
    //                 public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    //                     System.out.println("step 1");
    //                     return RepeatStatus.FINISHED;
    //                 }
    //             }).build();
    // }
    //
    // @Bean(name = "step2")
    // public Step step2() {
    //     return stepBuilderFactory.get("step2")
    //             .tasklet((contribution, context) -> {
    //                 System.out.println("step 2");
    //                 return RepeatStatus.FINISHED;
    //             }).build();
    // }
    //
    // @Bean(name = "step3")
    // public Step step3() {
    //     return stepBuilderFactory.get("step3")
    //             .tasklet(new Tasklet() {
    //                 @Override
    //                 public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    //                     System.out.println("step 3");
    //                     return RepeatStatus.FINISHED;
    //                 }
    //             }).build();
    // }

}
