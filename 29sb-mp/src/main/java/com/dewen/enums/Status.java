package com.dewen.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum Status implements IEnum {
    is_deleted("0", "未删除"), deleted("1", "删除");

    private String value;
    private String desc;

    private Status(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }
}
