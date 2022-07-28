package com.dewen.jobs.dewen0728.step;

import com.dewen.jobs.dewen0728.entity.Access;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DProcessConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DProcessConfiguration.class);

    // @Override
    // public Access process(Access access) throws Exception {
    //     // log.info("processor data : " + access.toString());
    //     System.out.println("process data : " + access.toString());
    //     return access;
    // }

    @Bean(name = "dProcess")
    @StepScope
    public ItemProcessor<Access, Access> dProcess() {
        // return new ItemProcessor<Access, Access>() {
        //     @Override
        //     public Access process(Access access) throws Exception {
        //         log.info("processor data : " + access.toString());
        //         return access;
        //     }
        // };
        //模拟处理
        return access -> {
            // log.info("processor data : " + access.toString());
            System.out.println("process data : " + access.toString());
            return access;
        };
    }
}
