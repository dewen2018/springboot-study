package com.dewen.sso;

import cn.dev33.satoken.config.SaSsoConfig;
import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.sso.SaSsoHandle;
import cn.dev33.satoken.sso.SaSsoUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.ejlchina.okhttps.OkHttps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * Sa-Token-SSO Server端 Controller
 */
@RestController
public class SsoServerController {

    /*
     * SSO-Server端：处理所有SSO相关请求
     * 		http://{host}:{port}/sso/auth			-- 单点登录授权地址，接受参数：redirect=授权重定向地址
     * 		http://{host}:{port}/sso/doLogin		-- 账号密码登录接口，接受参数：name、pwd
     * 		http://{host}:{port}/sso/checkTicket	-- Ticket校验接口（isHttp=true时打开），接受参数：ticket=ticket码、ssoLogoutCall=单点注销回调地址 [可选]
     * 		http://{host}:{port}/sso/logout			-- 单点注销地址（isSlo=true时打开），接受参数：loginId=账号id、secretkey=接口调用秘钥
     */
    @RequestMapping("/sso/*")
    public Object ssoRequest() {
        return SaSsoHandle.serverRequest();
    }

    // 配置SSO相关参数
    @Autowired
    private void configSso(SaSsoConfig sso) {

        // 配置：未登录时返回的View
        sso.setNotLoginView(() -> {
            return new ModelAndView("sa-login.html");
        });

        // 配置：登录处理函数
        sso.setDoLoginHandle((name, pwd) -> {
            // 此处仅做模拟登录，真实环境应该查询数据进行登录
            if ("sa".equals(name) && "123456".equals(pwd)) {
                StpUtil.login(10001);
                return SaResult.ok("登录成功！").setData(StpUtil.getTokenValue());
            }
            return SaResult.error("登录失败！");
        });

        // 配置 Http 请求处理器 （在模式三的单点注销功能下用到，如不需要可以注释掉）
        sso.setSendHttp(url -> {
            try {
                // 发起 http 请求
                System.out.println("发起请求：" + url);
                return OkHttps.sync(url).get().getBody().toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @GetMapping("/res")
    public String res() {
        return "you login status:" + StpUtil.isLogin();
    }

    // 自定义接口：获取userinfo
    @RequestMapping("/sso/userinfo")
    public Object userinfo(String loginId) {
        System.out.println("---------------- 获取userinfo --------");

        // 校验签名，防止敏感信息外泄
        SaSsoUtil.checkSign(SaHolder.getRequest());

        // 自定义返回结果（模拟）
        return SaResult.ok()
                .set("id", loginId)
                .set("name", "linxiaoyu")
                .set("sex", "女")
                .set("age", 18);
    }

    // 获取指定用户的关注列表
    @RequestMapping("/sso/getFollowList")
    public Object ssoRequest(Long loginId) {

        // 校验签名，签名不通过直接抛出异常
        SaSsoUtil.checkSign(SaHolder.getRequest());

        // 查询数据 (此处仅做模拟)
        List<Integer> list = Arrays.asList(10041, 10042, 10043, 10044);

        // 返回
        return list;
    }
}
