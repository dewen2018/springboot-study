package com.dewen.mybatisplus.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@TableName("t_user")
public class User extends Model<User> implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */

    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 逻辑删除(0-未删除,1-已删除)
     */
    @TableLogic
    private String delFlag;

    /**
     * 创建时间,允许为空,让数据库自动生成即可
     */
    private Date createTime;
}
