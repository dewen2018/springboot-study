package com.dewen.controller;

import com.dewen.entity.Book;
import com.dewen.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping(value = "book")
    public List<Book> getItems() {
        return bookService.getBookList();
    }

    @PostMapping(value = "/book")
    public Boolean saveItem(Book book) {
        return bookService.save(book);
    }
}