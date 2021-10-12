package com.dewen.protocol.message;

import lombok.Data;

@Data
public abstract class Packet {
    /**
     * 版本
     */
    private Byte version = 1;

    public abstract Byte getCommand();
}
