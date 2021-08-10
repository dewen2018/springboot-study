package com.dewen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value="mp_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Long tenantId;
}
