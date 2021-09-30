package com.dewen.constants;

import lombok.Getter;

/**
 * 任务状态
 **/
@Getter
public enum JobStatus {
    /**
     * 不可执行状态，等待时钟周期
     */
    DELAY,
    /**
     * 可执行状态，等待消费
     */
    READY,
    /**
     * 已被消费者读取，但还未得到消费者的响应
     */
    RESERVED,
    /**
     * 已被消费完成或者已被删除
     */
    DELETED;
}