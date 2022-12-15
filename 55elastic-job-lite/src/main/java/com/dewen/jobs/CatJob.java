package com.dewen.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;


public class CatJob implements SimpleJob {

    @Override
	public void execute(ShardingContext shardingContext) {
		int i = shardingContext.getShardingItem();
		switch (i) {
        case 0: 
           System.out.println("分片："+i+",分片对应自定义值："+shardingContext.getShardingParameter());
            break;
        case 1: 
        	 System.out.println("分片："+i+",分片对应自定义值："+shardingContext.getShardingParameter());
            break;
        case 2: 
        	 System.out.println("分片："+i+",分片对应自定义值："+shardingContext.getShardingParameter());
            break;
        default:
        	System.out.println("分片异常");
        	
    }
		
	}

}