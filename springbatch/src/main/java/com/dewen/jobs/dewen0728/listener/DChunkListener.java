package com.dewen.jobs.dewen0728.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.listener.ChunkListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public class DChunkListener extends ChunkListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(DChunkListener.class);

    @Override
    public void beforeChunk(ChunkContext context) {
        log.info("CHUNK STARTED");
        super.afterChunk(context);
    }

    @Override
    public void afterChunk(ChunkContext context) {
        //context.attributeNames();
        log.info("CHUNK FINISHED");
        super.beforeChunk(context);
    }

}