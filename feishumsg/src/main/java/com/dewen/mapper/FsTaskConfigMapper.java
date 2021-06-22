package com.dewen.mapper;

import com.dewen.entity.FsDepartment;
import com.dewen.entity.FsTaskConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 任务配置 Mapper 接口
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
public interface FsTaskConfigMapper extends BaseMapper<FsTaskConfig> {

    int insertFsTaskConfigs(@Param("fsTaskConfigs") List<FsTaskConfig> fsTaskConfigs);
}
