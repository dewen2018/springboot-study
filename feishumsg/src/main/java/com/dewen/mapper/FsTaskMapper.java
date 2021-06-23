package com.dewen.mapper;

import com.dewen.entity.FsTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dewen.entity.FsTaskConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 任务表 Mapper 接口
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
public interface FsTaskMapper extends BaseMapper<FsTask> {
    int insertFsTasks(@Param("fsTasks") List<FsTask> fsTasks);

    int updateFsTasks(@Param("fsTasks") List<FsTask> fsTasks);
}
