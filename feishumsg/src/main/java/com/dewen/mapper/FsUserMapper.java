package com.dewen.mapper;

import com.dewen.entity.FsDepartment;
import com.dewen.entity.FsUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
public interface FsUserMapper extends BaseMapper<FsUser> {

    int insertFsUsers(@Param("fsUsers") List<FsUser> fsUsers);
}
