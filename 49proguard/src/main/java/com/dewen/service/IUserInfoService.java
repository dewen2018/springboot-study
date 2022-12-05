package com.dewen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dewen.entity.UserInfo;

public interface IUserInfoService extends IService<UserInfo> {
    /**
     * 增加用户信息
     *
     * @param userInfo
     */
    void addUserInfo(UserInfo userInfo);

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    UserInfo getByName(Integer id);

    UserInfo getByName2(Integer id);

    UserInfo getByName3(Integer id);

    // /**
    //  * 修改用户信息
    //  *
    //  * @param userInfo
    //  * @return
    //  */
    // UserInfo updateUserInfo(UserInfo userInfo);
    //
    // /**
    //  * 删除用户信息
    //  *
    //  * @param id
    //  */
    // void deleteById(Integer id);
}