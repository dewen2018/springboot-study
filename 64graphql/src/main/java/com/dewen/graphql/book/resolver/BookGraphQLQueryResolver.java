package com.dewen.graphql.book.resolver;

import com.dewen.graphql.book.pojo.Author;
import com.dewen.graphql.book.pojo.Book;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BookGraphQLQueryResolver implements GraphQLQueryResolver {

    public Book findBookById(Integer id) {
        Book book = new Book();
        book.setId(UUID.randomUUID());
        book.setName("这边书没有书名");
        Author author =  Author.builder()
                .id(UUID.randomUUID())
                .name("Dewen")
                .build();
        book.setAuthor(author);
        return book;
    }
}