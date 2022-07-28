// package com.dewen.jobs.jobdemo;
//
// import com.dewen.jobs.jobdemo.listener.SimpleChunkListener;
// import com.dewen.jobs.jobdemo.listener.SimpleJobExecutionListener;
// import com.dewen.jobs.jobdemo.listener.SimpleStepExecutionListener;
// import com.dewen.jobs.jobdemo.param.CurrentTimeIncrementer;
// import org.mybatis.spring.batch.MyBatisBatchItemWriter;
// import org.mybatis.spring.batch.MyBatisPagingItemReader;
// import org.springframework.batch.core.Job;
// import org.springframework.batch.core.Step;
// import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
// import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
// import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
// import org.springframework.batch.core.step.builder.SimpleStepBuilder;
// import org.springframework.batch.item.ItemProcessor;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// //@Slf4j
// @Configuration
// @EnableBatchProcessing
// public class SimpleJobConfiguration {
//
//     @Autowired
//     private JobBuilderFactory jobBuilderFactory;
//
//     @Autowired
//     private StepBuilderFactory stepBuilderFactory;
//
//     @Bean(name = "simpleStepExecutionListener")
//     public SimpleStepExecutionListener simpleStepExecutionListener() {
//         return new SimpleStepExecutionListener();
//     }
//
//     @Bean(name = "simpleChunkListener")
//     public SimpleChunkListener simpleChunkListener() {
//         return new SimpleChunkListener();
//     }
//
//     @Bean(name = "jobCompletionNotificationListener")
//     public SimpleJobExecutionListener jobExecutionListener() {
//         return new SimpleJobExecutionListener();
//     }
//
//     @Bean
//     public Job simpleJob(@Qualifier("jobCompletionNotificationListener") SimpleJobExecutionListener jobListener, Step simpleStep1) {
//         return jobBuilderFactory.get("simpleJob")
//                 .incrementer(new CurrentTimeIncrementer())
//                 .listener(jobListener)
//                 .flow(simpleStep1)
//                 .end()
//                 .build();
//     }
//
//     @SuppressWarnings({"rawtypes", "unchecked"})
//     @Bean
//     public Step simpleStep1(@Qualifier("simpleStepExecutionListener") SimpleStepExecutionListener stepListener, @Qualifier("simpleChunkListener") SimpleChunkListener chunkListener,
//                             @Qualifier("peoplePagingItemReader") MyBatisPagingItemReader peoplePagingItemReader, @Qualifier("peopleItemProcess") ItemProcessor peopleItemProcess,
//                             @Qualifier("peopleBatchItemWriter") MyBatisBatchItemWriter peopleBatchItemWriter) {
//         return ((SimpleStepBuilder<Object, Object>) stepBuilderFactory.get("simpleStep1")
//                 .listener(stepListener)
//                 .chunk(10)
//                 .listener(chunkListener))
//                 .reader(peoplePagingItemReader)
//                 .processor(peopleItemProcess)
//                 .writer(peopleBatchItemWriter)
//                 .build();
//     }
//     /*
// 	@SuppressWarnings("rawtypes")
// 	@Bean("peopleItemProcess")
// 	@StepScope
// 	public ItemProcessor peopleItemProcess() {
// 		return new PeopleItemProcessor();
// 	}*/
// }
