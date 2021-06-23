package com.dewen.entity;

import lombok.Data;

@Data
public class Content {
    private String text;

    public Content(String text) {
        this.text = text;
    }
}
