package com.dewen.service;

import com.dewen.entity.FsTask;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 任务表 服务类
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
public interface IFsTaskService extends IService<FsTask> {

    void synFsTask();
}
