package com.dewen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dewen.graphql.student.pojo.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author dewen
 * @date 2023/1/7 16:34
 */
public interface StudentMapper extends BaseMapper<Student> {
    @Select("select min(id) from Student")
    Integer findMinId();

    @Select("select max(id) from Student")
    Integer findMaxId();

    List<Student> findByIdGreaterThan(Integer id, Page page);
}
