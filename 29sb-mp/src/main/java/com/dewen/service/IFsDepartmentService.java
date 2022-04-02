package com.dewen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dewen.entity.FsDepartment;

import java.text.ParseException;

/**
 * <p>
 * 部门信息 服务类
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
public interface IFsDepartmentService extends IService<FsDepartment> {
    void pageTest();

    void selectpage() throws ParseException;

    void getTest();
}
