package dev.amitgade.javaweb.books.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
    @GetMapping("/api")
    public String booksApi() {
        return "Welcome to Books API";
    }
}
