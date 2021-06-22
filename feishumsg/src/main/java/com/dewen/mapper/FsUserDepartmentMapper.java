package com.dewen.mapper;

import com.dewen.entity.FsUser;
import com.dewen.entity.FsUserDepartment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户部门 Mapper 接口
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
public interface FsUserDepartmentMapper extends BaseMapper<FsUserDepartment> {


    int insertFsUserDepartments(@Param("fsUserDepartments") List<FsUserDepartment> fsUserDepartments);
}
