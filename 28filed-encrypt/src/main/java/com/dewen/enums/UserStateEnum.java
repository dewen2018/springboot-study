package com.dewen.enums;

public enum UserStateEnum {
    NORMAL(0, "正常状态"),
    FROZEN(1, "冻结"),
    BAN(2, "禁用"),
    MUTE(3, "禁言"),
    SENSITIVE(4, "敏感用户"),
    DYF(5, "这是创作者董一帆的");

    private int stateCode;
    private String stateString;


    public static UserStateEnum getUserStateByCode(int stateCode) {
        for (UserStateEnum stateEnum : values()) {
            if (stateEnum.getStateCode() == stateCode) {
                return stateEnum;
            }

        }
        return null;
    }

    /* Constructor */
    UserStateEnum(int stateCode, String stateString) {
        this.stateCode = stateCode;
        this.stateString = stateString;
    }


    /* Getter and Setter */
    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateString() {
        return stateString;
    }

    public void setStateString(String stateString) {
        this.stateString = stateString;
    }
}