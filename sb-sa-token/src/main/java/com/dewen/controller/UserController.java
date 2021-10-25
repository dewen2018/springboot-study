package com.dewen.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对 
        if ("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    // 检验当前会话是否已经登录, 如果未登录，则抛出异常：`NotLoginException`
    @RequestMapping("checkLogin")
    public void checkLogin() {
        StpUtil.checkLogin();
    }

    @RequestMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    /**
     * // 获取当前会话账号id, 如果未登录，则抛出异常：`NotLoginException`
     * StpUtil.getLoginId();
     * <p>
     * // 类似查询API还有：
     * StpUtil.getLoginIdAsString();    // 获取当前会话账号id, 并转化为`String`类型
     * StpUtil.getLoginIdAsInt();       // 获取当前会话账号id, 并转化为`int`类型
     * StpUtil.getLoginIdAsLong();      // 获取当前会话账号id, 并转化为`long`类型
     * <p>
     * // ---------- 指定未登录情形下返回的默认值 ----------
     * <p>
     * // 获取当前会话账号id, 如果未登录，则返回null
     * StpUtil.getLoginIdDefaultNull();
     * <p>
     * // 获取当前会话账号id, 如果未登录，则返回默认值 （`defaultValue`可以为任意类型）
     * StpUtil.getLoginId(T defaultValue);
     * <p>
     * // 获取指定token对应的账号id，如果未登录，则返回 null
     * StpUtil.getLoginIdByToken(String tokenValue);
     * <p>
     * // 获取当前`StpLogic`的token名称
     * StpUtil.getTokenName();
     * <p>
     * // 获取当前会话的token值
     * StpUtil.getTokenValue();
     * <p>
     * // 获取当前会话的token信息参数
     * StpUtil.getTokenInfo();
     */
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

}
