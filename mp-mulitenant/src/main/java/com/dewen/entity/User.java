package com.dewen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * dj 2021年8月10日11:42:00
 */
@Data
@TableName(value = "mp_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Long tenantId;
}
