package com.dewen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dewen.entity.FsUserAppTable;

/**
 * <p>
 * 用户多维表格 服务类
 * </p>
 *
 * @author dj
 * @since 2021-06-29
 */
public interface IFsUserAppTableService extends IService<FsUserAppTable> {
    void createFsUserAppTable();


    void synTasks();
}
