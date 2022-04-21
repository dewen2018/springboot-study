package com.dewen.job.listener;

import org.springframework.batch.core.listener.ChunkListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleChunkListener extends ChunkListenerSupport {

    @Override
    public void beforeChunk(ChunkContext context) {
    	log.info("[CHUNK]-------------------[STARTED]");
        super.afterChunk(context);
    }

    @Override
    public void afterChunk(ChunkContext context) {
        //context.attributeNames();
        log.info("[CHUNK]-------------------[FINISHED]");
        super.beforeChunk(context);
    }

}