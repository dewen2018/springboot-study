package com.dewen.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "dewen_book")
public class Book {

    /**
     * mongo 自增 id
     */
    @Id
    private String id;

    /**
     * 图书名称
     */
    private String name;

    /**
     * 价格
     */
    private double price;

    /**
     * 出版社
     */
    private String press;

    /**
     * 作者
     */
    private Author[] authors;

    /**
     * 标签
     */
    private String[] tags;

    public Book(String name, double price, String press, Author[] authors, String[] tags) {
        this.name = name;
        this.price = price;
        this.press = press;
        this.authors = authors;
        this.tags = tags;
    }

    public Book() {
    }
}