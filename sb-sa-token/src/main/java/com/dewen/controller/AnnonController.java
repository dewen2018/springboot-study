//package com.dewen.controller;
//
//import cn.dev33.satoken.annotation.SaCheckLogin;
//import cn.dev33.satoken.annotation.SaCheckPermission;
//import cn.dev33.satoken.annotation.SaMode;
//import cn.dev33.satoken.util.SaResult;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///**
// * 注解式鉴权
// * 1、注册拦截器
// * 2、使用注解鉴权
// * 3、设定校验模式
// * 4、角色权限双重 “or校验”
// * 5、在业务逻辑层使用注解鉴权
// */
//public class AnnonController {
//    // 登录认证：只有登录之后才能进入该方法
//    @SaCheckLogin
//    @RequestMapping("info")
//    public String info() {
//        return "查询用户信息";
//    }
//
//    // 角色认证：必须具有指定角色才能进入该方法
////    @SaCheckRole("super-admin")
////    @RequestMapping("add")
////    public String add() {
////        return "用户增加";
////    }
////
////    // 权限认证：必须具有指定权限才能进入该方法
////    @SaCheckPermission("user-add")
////    @RequestMapping("add")
////    public String add() {
////        return "用户增加";
////    }
//
//    // 二级认证：必须二级认证之后才能进入该方法
////    @SaCheckSafe()
////    @RequestMapping("add")
////    public String add() {
////        return "用户增加";
////    }
////
////    // Http Basic 认证：只有通过 Basic 认证后才能进入该方法
////    @SaCheckBasic(account = "sa:123456")
////    @RequestMapping("add")
////    public String add() {
////        return "用户增加";
////    }
//
//
//    // 注解式鉴权：只要具有其中一个权限即可通过校验
//    @RequestMapping("atJurOr")
//    @SaCheckPermission(value = {"user-add", "user-all", "user-delete"}, mode = SaMode.OR)
//    public SaResult atJurOr() {
//        return SaResult.ok();
//    }
//
//    // 注解式鉴权：只要具有其中一个权限即可通过校验
//    @RequestMapping("userAdd")
//    @SaCheckPermission(value = "user-add", orRole = "admin")
//    public SaResult userAdd() {
//        return SaResult.ok("用户信息");
//    }
//
//}
