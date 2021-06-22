package com.dewen.entity;

    import java.time.LocalDateTime;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

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
    @ApiModel(value="FsTask对象", description="任务表")
    public class FsTask implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "飞书表格行id")
    private String taskId;

            @ApiModelProperty(value = "任务名称")
    private String taskName;

            @ApiModelProperty(value = "任务类型")
    private String taskType;

            @ApiModelProperty(value = "发布人id")
    private String issuerOpenId;

            @ApiModelProperty(value = "发布人")
    private String issuer;

            @ApiModelProperty(value = "发布时间")
    private LocalDateTime releaseTime;

            @ApiModelProperty(value = "执行人")
    private String executor;


}
