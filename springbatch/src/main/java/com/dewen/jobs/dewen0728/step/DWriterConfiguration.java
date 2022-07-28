package com.dewen.jobs.dewen0728.step;

import com.dewen.jobs.dewen0728.entity.Access;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DWriterConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DWriterConfiguration.class);

    @Bean(name = "dWriter")
    @StepScope
    public ItemWriter<Access> itemWriter() {
        return list -> {
            for (Access access : list) {
                // log.info("write data : " + access.toString());
                System.out.println("write data : " + access.toString());
            }
        };
    }
}
