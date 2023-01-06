package com.dewen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dewen.annotations.EncryptTransaction;
import com.dewen.annotations.SensitiveData;
import com.dewen.handlers.AESEncryptHandler;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName(autoResultMap = true)
// 插件只对加了该注解的类进行扫描，只有加了这个注解的类才会生效
@SensitiveData
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String openId;
    private String password;
    // 表明对该字段进行加密
    @EncryptTransaction
    private String email;
    @TableField(typeHandler = AESEncryptHandler.class)
    private String mobile;
    private Date createTime;
    private Date expireTime;
    private Boolean status = true;
}