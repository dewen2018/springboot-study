// package com.dewen.jobs.jobdemo.step.process;
//
// import com.dewen.jobs.jobdemo.model.vo.People;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.batch.item.ItemProcessor;
//
// @Slf4j
// public class PeopleItemProcessor implements ItemProcessor<People, People> {
//
//     @Override
//     public People process(People item) throws Exception {
//         log.error("### first -> second : item = {}", item.toString());
//         return item;
//     }
//
// }
