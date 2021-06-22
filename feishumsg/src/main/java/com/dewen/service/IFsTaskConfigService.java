package com.dewen.service;

import com.dewen.entity.FsTaskConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 任务配置 服务类
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
public interface IFsTaskConfigService extends IService<FsTaskConfig> {

    void synFsTaskConfig();
}
