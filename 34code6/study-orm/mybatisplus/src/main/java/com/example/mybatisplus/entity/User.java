package com.example.mybatisplus.entity;

import lombok.Data;

@Data//默认提供Getter,Setter,equals,hashCode,toString方法
public class User {
    private Long id;
    private String name;
}
