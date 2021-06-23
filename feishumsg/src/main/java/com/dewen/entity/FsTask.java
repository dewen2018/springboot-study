package com.dewen.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 任务表
 * </p>
 *
 * @author dj
 * @since 2021-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "FsTask对象", description = "任务表")
public class FsTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "飞书表格行id")
    @TableId(value = "task_id")
    private String taskId;

    @ApiModelProperty(value = "任务名称")

    private String taskName;

    @ApiModelProperty(value = "任务类型")
    private String taskType;

    @ApiModelProperty(value = "任务状态")
    private String taskStatus;

    @ApiModelProperty(value = "发布人")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Person> issuer;

    @ApiModelProperty(value = "发布时间")
    private Timestamp releaseTime;

    @ApiModelProperty(value = "执行人")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Person> executor;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    @JSONField(name = "create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time")
    @JSONField(name = "update_time")
    private Date updateTime;

    @Data
    @ApiModel("执行人实体类")
    public static class Person {

        private String id;

        private String name;

        @JSONField(name = "en_name")
        private String enName;

        private String email;

        @JSONField(name = "annotation_union_type")
        private String annotationUnionType;
    }
}
