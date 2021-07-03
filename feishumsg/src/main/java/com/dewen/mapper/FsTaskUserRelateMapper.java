package com.dewen.mapper;

import com.dewen.entity.FsTaskUserRelate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dewen.entity.FsTaskUserRelateDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 任务人员配置 Mapper 接口
 * </p>
 *
 * @author dj
 * @since 2021-07-01
 */
public interface FsTaskUserRelateMapper extends BaseMapper<FsTaskUserRelate> {

    List<FsTaskUserRelateDTO> findFsTaskUserRelateDTOListByTaskType(@Param("taskType") String taskType);

}
