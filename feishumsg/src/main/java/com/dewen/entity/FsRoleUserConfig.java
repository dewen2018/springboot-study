package com.dewen.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色配置人员
 * </p>
 *
 * @author dj
 * @since 2021-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FsRoleUserConfig对象", description="角色配置人员")
public class FsRoleUserConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "人员")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<FsTask.Person> users;
}
