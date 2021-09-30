package com.dewen.controller;

import com.alibaba.fastjson.JSON;
import com.dewen.bean.DelayJob;
import com.dewen.bean.Job;
import com.dewen.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试请求
 **/
@RestController
@RequestMapping("delay")
public class DelayController {

    @Autowired
    private JobService jobService;

    private final static AtomicInteger index = new AtomicInteger(0);

    private final static String[] tag = new String[]{"test", "web", "java"};


    /**
     * 添加 测试的时候使用
     *
     * @return
     */
    @PostMapping(value = "addTest")
    public String addDefJobTest() {
        Job request = new Job();
        int i = index.addAndGet(1);
        Long aLong = Long.valueOf(i);
        request.setId(aLong);
        int num = i % 3;
        request.setTopic(tag[num]);
        request.setMessage("tag:" + tag[num] + "id:" + i);
        request.setDelayTime(10000);
        request.setTtrTime(10000);
        DelayJob delayJob = jobService.addDefJob(request);
        return JSON.toJSONString(delayJob);
    }

    /**
     * 添加
     *
     * @param request
     * @return
     */
    @PostMapping(value = "add")
    public String addDefJob(Job request) {
        DelayJob delayJob = jobService.addDefJob(request);
        return JSON.toJSONString(delayJob);
    }

    /**
     * 获取
     *
     * @return
     */
    @GetMapping(value = "pop/{topic}")
    public String getProcessJob(@PathVariable("topic") String topic) {
        Job process = jobService.getProcessJob(topic);
        return JSON.toJSONString(process);
    }

    /**
     * 完成一个执行的任务
     *
     * @param jobId
     * @return
     */
    @DeleteMapping(value = "finish")
    public String finishJob(Long jobId) {
        jobService.finishJob(jobId);
        return "success";
    }

    @DeleteMapping(value = "delete")
    public String deleteJob(Long jobId) {
        jobService.deleteJob(jobId);
        return "success";
    }

}