package com.springboot.quickstart.dao;

import com.springboot.quickstart.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class BookDAO {

    public List<Book> getBook() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "SpringBoot实战", 88.7));
        books.add(new Book(2, "SpringMVC", 98.7));
        books.add(new Book(3, "Spring", 80.7));
        books.add(new Book(4, "SpringBoot", 100.2));
        return books;

    }
}

