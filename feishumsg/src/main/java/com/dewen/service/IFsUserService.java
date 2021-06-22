package com.dewen.service;

import com.dewen.entity.FsUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
public interface IFsUserService extends IService<FsUser> {

    void synFsUser();
}
