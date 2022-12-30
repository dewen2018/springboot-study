package com.dewen.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author lyr
 * @since 2020-10-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("WORKER_NODE")
public class WorkerNode extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * auto increment id
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * host name
     */
    @TableField("HOST_NAME")
    private String hostName;

    /**
     * port
     */
    @TableField("PORT")
    private String port;

    /**
     * node type: ACTUAL or CONTAINER
     */
    @TableField("TYPE")
    private Integer type;

    /**
     * launch date
     */
    @TableField("LAUNCH_DATE")
    private LocalDate launchDate;

    /**
     * modified time
     */
    @TableField("MODIFIED")
    private LocalDateTime modified;

    /**
     * created time
     */
    @TableField("CREATED")
    private LocalDateTime created;


}
