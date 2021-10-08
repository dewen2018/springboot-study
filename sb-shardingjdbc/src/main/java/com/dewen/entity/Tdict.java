package com.dewen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@TableName("t_dict")
public class Tdict implements Serializable {
    @TableId(type = IdType.NONE)
    private Integer id;
    private String dictName;
    private String dictValue;
}
