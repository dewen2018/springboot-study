package com.dewen.service;

import com.dewen.entity.FsDepartment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 部门信息 服务类
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
public interface IFsDepartmentService extends IService<FsDepartment> {

    void synFsDepartment();

}
