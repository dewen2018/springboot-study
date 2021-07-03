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
import java.sql.Timestamp;
import java.util.Date;

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
    @TableId(value = "id")
    private String id;

    @ApiModelProperty(value = "对应的具体任务")
    @TableField(value = "task_id")
    private String taskId;

    @ApiModelProperty(value = "0我创建的任务1我收到的任务2总表")
    @TableField(value = "task_classify")
    private Integer taskClassify;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "任务类型")
    private String taskType;

    @ApiModelProperty(value = "任务状态")
    private String taskStatus;

    @ApiModelProperty(value = "发布人")
    @TableField(value = "issuer")
    private String issuer;

    @ApiModelProperty(value = "发布时间")
    private Timestamp releaseTime;

    @ApiModelProperty(value = "执行人")
    @TableField(value = "executor")
    private String executor;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    @JSONField(name = "create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time")
    @JSONField(name = "update_time")
    private Date updateTime;

    @ApiModelProperty(value = "用户openId")
    @TableField(value = "user_open_id")
    private String userOpenId;

    @ApiModelProperty(value = "用户多维表格应用")
    @TableField(value = "app_token")
    private String appToken;

    @ApiModelProperty(value = "表id")
    @TableField(value = "table_id")
    private String tableId;

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
