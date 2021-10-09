package com.dewen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@TableName("t_order_item")
public class OrderItem implements Serializable {
    @TableId(type = IdType.NONE, value = "id")
    private Integer id;
    private Integer orderId;
    private String goodName;
    private Integer userId;
}
