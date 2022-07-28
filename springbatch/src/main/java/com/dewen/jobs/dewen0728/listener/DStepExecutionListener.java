package com.dewen.jobs.dewen0728.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class DStepExecutionListener extends StepExecutionListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(DStepExecutionListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("STEP STARTED");
        // super.beforeStep(stepExecution);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("STEP FINISHED");
        return super.afterStep(stepExecution);
    }
}