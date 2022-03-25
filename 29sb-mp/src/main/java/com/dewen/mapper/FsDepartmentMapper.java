package com.dewen.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dewen.entity.FsDepartment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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

    @Select("${ew}")
    IPage<FsDepartment> pageTest(@Param("page") Page<FsDepartment> page, @Param("ew") String sql);

    IPage<Map<String, Object>> selectpage(@Param("page") Page page);
//    IPage<JSONObject> selectpage(@Param("page") Page page);
}
