package com.dewen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dewen.annotations.EncryptTransaction;
import com.dewen.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
    // 只需要在参数前加上@EncryptTransaction 即可
    @Select("select count(*) cnt from user where email = '${email}'")
    long countByEmail(@EncryptTransaction @Param("email") String email);

    @Select("select count(*) cnt from user where email = '${mobile}'")
    long countByMobile(@EncryptTransaction @Param("mobile") String mobile);
}