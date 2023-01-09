package com.dewen.graphql.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class User {
    private UUID id;
    private String username;
    private String nickname;
    private City city;
}