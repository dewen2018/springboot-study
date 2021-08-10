package com.dewen.mapper;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dewen.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * dj 2021年8月10日11:42:00
 */
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
