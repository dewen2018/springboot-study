package com.dewen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dewen.entity.DewenUser;

import java.util.List;

/**
 * @author dewen
 * @date 2023/1/4 21:24
 */
public interface DewenUserMapper extends BaseMapper<DewenUser> {
    DewenUser selectByPrimaryKey(Integer id);

    List<DewenUser> findAllUser();
}
