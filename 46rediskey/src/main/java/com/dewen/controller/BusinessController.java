package com.dewen.controller;

import com.dewen.utlis.RedisUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/set")
    public String set(String id, String name) {
        RedisUtils.setValueTimeout("demo:ids:" + id, name, 30);
        if (name.equals("tel")) {
            throw new RuntimeException("爆炸啦啊");
        }
        return "操作成功";
    }

    @GetMapping("/get")
    public String get(String id) {
        if (RedisUtils.getValue("demo:ids:" + id) != null) {
            return RedisUtils.getValue("demo:ids:" + id).toString();
        }
        return "id已过期";
    }

    @GetMapping("/del")
    public String del(String id) {
        boolean b = RedisUtils.delKey("demo:ids:" + id);
        return b ? "操作成功" : "操作失败";
    }

}
