package com.dewen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户多维表格
 * </p>
 *
 * @author dj
 * @since 2021-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "FsUserAppTable对象", description = "用户多维表格数据表")
public class FsUserAppTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户openId")
    private String userOpenId;

    @ApiModelProperty(value = "用户多维表格应用")
    private String appToken;

    @ApiModelProperty(value = "表id")
    private String tableId;

    @ApiModelProperty(value = "是否是创建任务")
    private Boolean isBuildTask;

    @ApiModelProperty(value = "是否是总表")
    private Boolean isAdmin;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
