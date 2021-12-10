package com.dewen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor  // 定义无参构造器是因为JPA需要
@AllArgsConstructor
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键自增长
    private Long id;
    private Integer age;
    @Column(name = "user_name")
    private String name;
    @Column(name = "create_time")
    private Date createTime;

}