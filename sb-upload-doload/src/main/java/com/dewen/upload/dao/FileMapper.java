package com.dewen.upload.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dewen.upload.entity.FileDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMapper extends BaseMapper<FileDTO> {
}