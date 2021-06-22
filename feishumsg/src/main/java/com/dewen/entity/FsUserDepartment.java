package com.dewen.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户部门
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "FsUserDepartment对象", description = "用户部门")
public class FsUserDepartment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableField(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "用户部门id")
    @TableField(value = "open_department_id")
    private String openDepartmentId;

    public FsUserDepartment(String userId, String openDepartmentId) {
        this.userId = userId;
        this.openDepartmentId = openDepartmentId;
    }
}
