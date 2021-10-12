package com.dewen.protocol.message;


import com.dewen.protocol.message.command.Command;

public class HeartbeatResponsePacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}
