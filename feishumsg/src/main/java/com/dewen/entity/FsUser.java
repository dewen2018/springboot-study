package com.dewen.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "FsUser对象", description = "用户信息")
public class FsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "user_id")
    @JSONField(name = "user_id")
    private String userId;

    @ApiModelProperty(value = "用户的 union id")
    @TableField(value = "union_id")
    @JSONField(name = "union_id")
    private String unionId;

    @ApiModelProperty(value = "用户的 open id")
    @TableField(value = "open_id")
    @JSONField(name = "open_id")
    private String openId;

    @ApiModelProperty(value = "用户名")
    @TableField(value = "name")
    @JSONField(name = "name")
    private String name;

    @ApiModelProperty(value = "手机号")
    @TableField(value = "mobile")
    @JSONField(name = "mobile")
    private String mobile;

    /**
     * 0：保密
     * 1：男
     * 2：女
     */
    @ApiModelProperty(value = "性别")
    @TableField(value = "gender")
    @JSONField(name = "gender")
    private Integer gender;

//    @ApiModelProperty(value = "部门名称")
//    @TableField(value = "name")
//    @JSONField(name = "name")
//    private String status;

    @TableField(exist = false)
    @JSONField(name = "department_ids")
    private List<String> departmentIds;
}
