package com.dewen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.security.Timestamp;

/**
 * @author dewen
 * @date 2022/12/30 17:44
 */
@Data
@TableName("user")
public class GroovyScript implements Serializable {
    @TableId(type = IdType.AUTO, value = "id")
    private Integer id;
    private String scriptName;
    private String scriptContent;
    private String status;
    private String extendInfo;
    private Timestamp createdTime;
    private Timestamp modifiedTime;
}
