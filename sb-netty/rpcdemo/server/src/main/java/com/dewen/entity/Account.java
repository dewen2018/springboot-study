package com.dewen.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Account implements Serializable {
    private static final long serialVersionUID = 667178018106218163L;
    private Integer id;

    private String name;
    private String username;
    private String password;
}