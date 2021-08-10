package com.dewen.mapper;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dewen.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    @Select("select * from mp_user")
    List<User> selectUserList();

    // 方式二：通过租户注解 @SqlParser(filter = true) 的形式，目前只能作用于Mapper的方法上
    @SqlParser(filter = true)
    @Select("select * from mp_user")
    List<User> selectUserList2();

    /**
     * 自定Wrapper修改
     *
     * @param userWrapper 条件构造器
     * @param user        修改的对象参数
     * @return
     */
//    @SqlParser(filter = true)
//    int updateByMyWrapper(@Param(Constants.WRAPPER) Wrapper<User> userWrapper, @Param("user") User user);
}
