package com.dewen.service;

import com.dewen.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBookList();

    boolean save(Book book);
}
