package com.dewen.graphql.book.pojo;

import lombok.Data;

import java.util.UUID;

@Data
public class Book {
    private UUID id;
    private String name;
    private Author author;
}