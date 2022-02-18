package com.dewen.entity;


import com.dewen.enums.UserStateEnum;
import com.dewen.typehandler.Encrypt;

import java.util.List;

public class Customer {

    private Integer id;

    private Encrypt phone;

    private List<String> address;

    private UserStateEnum state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Encrypt getPhone() {
        return phone;
    }

    public void setPhone(Encrypt phone) {
        this.phone = phone;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public UserStateEnum getState() {
        return state;
    }

    public void setState(UserStateEnum state) {
        this.state = state;
    }
}
