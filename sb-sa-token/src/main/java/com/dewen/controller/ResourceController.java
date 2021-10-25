package com.dewen.controller;


import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("resource")
public class ResourceController {

    @RequestMapping("verPer")
    public void verPer() {
        // 判断：当前账号是否含有指定权限, 返回true或false
        StpUtil.hasPermission("user-update");

        // 校验：当前账号是否含有指定权限, 如果验证未通过，则抛出异常: NotPermissionException
        StpUtil.checkPermission("user-update");

        // 校验：当前账号是否含有指定权限 [指定多个，必须全部验证通过]
        StpUtil.checkPermissionAnd("user-update", "user-delete");

        // 校验：当前账号是否含有指定权限 [指定多个，只要其一验证通过即可]
        StpUtil.checkPermissionOr("user-update", "user-delete");
    }

    @RequestMapping("verRole")
    public void verRole() {
        // 判断：当前账号是否拥有指定角色, 返回true或false
        StpUtil.hasRole("super-admin");

        // 校验：当前账号是否含有指定角色标识, 如果验证未通过，则抛出异常: NotRoleException
        StpUtil.checkRole("super-admin");

        // 校验：当前账号是否含有指定角色标识 [指定多个，必须全部验证通过]
        StpUtil.checkRoleAnd("super-admin", "shop-admin");

        // 校验：当前账号是否含有指定角色标识 [指定多个，只要其一验证通过即可]
        StpUtil.checkRoleOr("super-admin", "shop-admin");

    }
    /**
     * Sa-Token允许你根据通配符指定泛权限，例如当一个账号拥有user*的权限时，user-add、user-delete、user-update都将匹配通过
     * <button v-if="arr.indexOf('user:delete') > -1">删除按钮</button>
     * 前端有了鉴权后端还需要鉴权吗？
     * 需要！
     *
     * 前端的鉴权只是一个辅助功能，对于专业人员这些限制都是可以轻松绕过的，为保证服务器安全，无论前端是否进行了权限校验，后端接口都需要对会话请求再次进行权限校验！
     *
     */
}
