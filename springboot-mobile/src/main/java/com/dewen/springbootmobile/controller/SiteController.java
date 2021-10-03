//package com.dewen.springbootmobile.controller;
//
//import org.springframework.mobile.device.site.SitePreference;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class SiteController {
//
//    @RequestMapping("/showcase/{name}")
//    public String home(SitePreference sitePreference, @PathVariable(value = "name")String name) {
//        StringBuffer restSb = new StringBuffer("输入用户:").append(name);
//        restSb.append("<br/>").append("使用设备为：").append(sitePreference.name());
//        restSb.append("<br/>").append("访问接口为：").append("showcase");
//        return restSb.toString();
//    }
//
//    @RequestMapping("/showcase/t/{name}")
//    public String homeT(SitePreference sitePreference, @PathVariable(value = "name")String name) {
//        StringBuffer restSb = new StringBuffer("输入用户:").append(name);
//        restSb.append("<br/>").append("使用设备为：").append(sitePreference.name());
//        restSb.append("<br/>").append("访问接口为：").append("/showcase/t");
//        return restSb.toString();
//    }
//
//    @RequestMapping("/showcase/m/{name}")
//    public String homeM(SitePreference sitePreference, @PathVariable(value = "name")String name) {
//        StringBuffer restSb = new StringBuffer("输入用户:").append(name);
//        restSb.append("<br/>").append("使用设备为：").append(sitePreference.name());
//        restSb.append("<br/>").append("访问接口为：").append("/showcase/m");
//        return restSb.toString();
//    }
//}
