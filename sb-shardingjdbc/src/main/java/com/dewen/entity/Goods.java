package com.dewen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class Goods implements Serializable {

    @TableId(type = IdType.NONE)
    private Integer id;
    private String goodsName;
    private BigDecimal money;
    private Integer stock;
    private Date createTime;
}
