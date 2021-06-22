package com.dewen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dewen.entity.FsDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 部门信息 Mapper 接口
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
public interface FsDepartmentMapper extends BaseMapper<FsDepartment> {

    int insertFsDepartments(@Param("fsDepartments") List<FsDepartment> fsDepartments);

    List<FsDepartment> selectALl();
}
