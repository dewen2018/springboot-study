package com.dewen.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FsTaskUserRelateDTO {
    @ApiModelProperty(value = "用户openId")
    private String openId;
    
    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "接收任务用的应用")
    private String recAppToken;

    @ApiModelProperty(value = "接收任务数据表")
    private String recTableId;
}
