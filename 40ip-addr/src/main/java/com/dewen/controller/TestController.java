package com.dewen.controller;

import com.dewen.config.Ip2regionSearcher;
import com.dewen.config.IPInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private Ip2regionSearcher ip2regionSearcher;


    /**
     * http://localhost:8082/node2/getIpInfo/1.2.3.4
     * http://localhost:8082/node2/getIpInfo/119.23.47.158
     *
     * @param ip
     * @return
     */
    @GetMapping("/getIpInfo/{ip}")
    public IPInfo getIpInfo(@PathVariable(value = "ip") String ip) {
        return ip2regionSearcher.search(ip);
    }
}
