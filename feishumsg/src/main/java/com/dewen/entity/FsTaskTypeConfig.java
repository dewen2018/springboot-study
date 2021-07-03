package com.dewen.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 飞书任务类型
 * </p>
 *
 * @author dj
 * @since 2021-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FsTaskTypeConfig对象", description="飞书任务类型")
public class FsTaskTypeConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "任务类型")
    private String taskType;

    @ApiModelProperty(value = "角色")
    private String roles;


}
