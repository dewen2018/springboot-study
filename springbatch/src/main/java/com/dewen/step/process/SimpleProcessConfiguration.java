package com.dewen.step.process;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Slf4j
@Configuration
public class SimpleProcessConfiguration {
	@SuppressWarnings("rawtypes")
	@Bean("peopleItemProcess")
	@StepScope
	public ItemProcessor peopleItemProcess() {
		return new PeopleItemProcessor();
	}
}
