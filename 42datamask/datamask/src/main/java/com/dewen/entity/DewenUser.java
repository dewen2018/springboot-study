package com.dewen.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dewen.desensitize.annotation.SensitiveField;
import com.dewen.desensitize.enums.SensitiveType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@TableName("dewen_user")
public class DewenUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Integer id;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 姓名
     */
    @SensitiveField(SensitiveType.CHINESE_NAME)
    private String name;

    /**
     * 邮箱
     */
    @SensitiveField(SensitiveType.EMAIL)
    private String email;

    /**
     * 手机号
     */
    @SensitiveField(SensitiveType.MOBILE)
    private String mobile;

    /**
     * 地址
     */
    @SensitiveField(SensitiveType.ADDRESS)
    private String address;
}
