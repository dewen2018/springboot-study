package com.dewen.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务类别
 **/
@AllArgsConstructor
@Getter
public enum JobTopic {

    TOPIC_ONE("one"),
    TOPIC_TWO("two");

    private String topic;
}
