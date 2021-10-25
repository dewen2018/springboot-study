package com.dewen.config;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Component    // 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展 
public class StpInterfaceImpl implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<String>();
        list.add("101");
        list.add("user-add");
        list.add("user-delete");
        list.add("user-update");
        list.add("user-get");
        list.add("article-get");
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        list.add("admin");
        list.add("super-admin");
        return list;
    }


    /**
     * 权限认证
     * // 判断：当前账号是否含有指定权限, 返回true或false
     * StpUtil.hasPermission("user-update");
     *
     * // 校验：当前账号是否含有指定权限, 如果验证未通过，则抛出异常: NotPermissionException
     * StpUtil.checkPermission("user-update");
     *
     * // 校验：当前账号是否含有指定权限 [指定多个，必须全部验证通过]
     * StpUtil.checkPermissionAnd("user-update", "user-delete");
     *
     * // 校验：当前账号是否含有指定权限 [指定多个，只要其一验证通过即可]
     * StpUtil.checkPermissionOr("user-update", "user-delete");
     * 角色认证
     * // 判断：当前账号是否拥有指定角色, 返回true或false
     * StpUtil.hasRole("super-admin");
     *
     * // 校验：当前账号是否含有指定角色标识, 如果验证未通过，则抛出异常: NotRoleException
     * StpUtil.checkRole("super-admin");
     *
     * // 校验：当前账号是否含有指定角色标识 [指定多个，必须全部验证通过]
     * StpUtil.checkRoleAnd("super-admin", "shop-admin");
     *
     * // 校验：当前账号是否含有指定角色标识 [指定多个，只要其一验证通过即可]
     * StpUtil.checkRoleOr("super-admin", "shop-admin");
     */


    /**
     *  // 返回一个账号所拥有的权限码集合
     *     @Override
     *     public List<String> getPermissionList(Object loginId, String loginType) {
     *
     *         // 1. 声明权限码集合
     *         List<String> permissionList = new ArrayList<>();
     *
     *         // 2. 遍历角色列表，查询拥有的权限码
     *         for (String roleId : getRoleList(loginId, loginType)) {
     *             SaSession roleSession = SaSessionCustomUtil.getSessionById("role-" + roleId);
     *             List<String> list = roleSession.get("Permission_List", () -> {
     *                 return ...;     // 从数据库查询这个角色所拥有的权限列表
     *             });
     *             permissionList.addAll(list);
     *         }
     *
     *         // 3. 返回权限码集合
     *         return permissionList;
     *     }
     *
     *     // 返回一个账号所拥有的角色标识集合
     *     @Override
     *     public List<String> getRoleList(Object loginId, String loginType) {
     *         SaSession session = StpUtil.getSessionByLoginId(loginId);
     *         return session.get("Role_List", () -> {
     *             return ...; // 从数据库查询这个账号id拥有的角色列表
     *         });
     *     }
     */
}
