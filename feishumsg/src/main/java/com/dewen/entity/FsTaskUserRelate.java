package com.dewen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 任务人员配置
 * </p>
 *
 * @author dj
 * @since 2021-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "FsTaskUserRelate对象", description = "任务人员配置")
public class FsTaskUserRelate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "任务类型")
    private String taskType;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "用户openId")
    private String openId;

    @ApiModelProperty(value = "名字")
    private String name;


}
