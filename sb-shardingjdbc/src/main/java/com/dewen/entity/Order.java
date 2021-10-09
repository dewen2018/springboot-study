package com.dewen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@TableName("t_order")
public class Order implements Serializable {
    @TableId(type = IdType.NONE, value = "id")
    private Integer id;
    @TableField(value = "user_id")
    private Integer userId;
    @TableField(value = "order_no")
    private String orderNo;
}
