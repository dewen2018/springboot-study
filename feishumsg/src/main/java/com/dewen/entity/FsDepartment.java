package com.dewen.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dewen.enums.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 部门信息
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "FsDepartment对象", description = "部门信息")
public class FsDepartment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门id")
    @TableId(value = "open_department_id")
    @JSONField(name = "open_department_id")
    private String openDepartmentId;

    @ApiModelProperty(value = "部门名称")
    @TableField(value = "name")
    @JSONField(name = "name")
    private String name;

    @ApiModelProperty(value = "部门领导id")
    @TableField(value = "leader_user_id")
    @JSONField(name = "leader_user_id")
    private String leaderUserId;

    @ApiModelProperty(value = "部门人数")
    @TableField(value = "member_count")
    @JSONField(name = "member_count")
    private String memberCount;

    @ApiModelProperty(value = "父部门id")
    @TableField(value = "parent_department_id")
    @JSONField(name = "parent_department_id")
    private String parentDepartmentId;

//    @ApiModelProperty(value = "是否删除")
//    @TableField(value = "status")
//    @JSONField(name = "status")
//    private Status status;

    @ApiModelProperty(value = "排序")
    @TableField(value = "order")
    @JSONField(name = "order")
    private Integer order;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    @JSONField(name = "create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time")
    @JSONField(name = "update_time")
    private Date updateTime;
}
