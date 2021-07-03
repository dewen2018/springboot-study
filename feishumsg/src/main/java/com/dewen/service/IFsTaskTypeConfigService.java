package com.dewen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dewen.entity.FsTaskTypeConfig;

/**
 * <p>
 * 飞书任务类型 服务类
 * </p>
 *
 * @author dj
 * @since 2021-07-01
 */
public interface IFsTaskTypeConfigService extends IService<FsTaskTypeConfig> {
    void synConfigInfo();
}
