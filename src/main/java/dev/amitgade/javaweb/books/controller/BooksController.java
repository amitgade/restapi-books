package dev.amitgade.javaweb.books.controller;

import dev.amitgade.javaweb.books.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BooksController {
    private final List<Book> books = new ArrayList<>();

    /*
    @GetMapping("/api")
    public String booksApi() {
        return "Welcome to Books API";
    }
    */

    public BooksController() {
        initializeBooks();
    }

    public void initializeBooks() {
        books.addAll(List.of(
                new Book("title one", "author one", "science"),
                new Book("title two", "author two", "math"),
                new Book("title three", "author three", "history"),
                new Book("title four", "author four", "science"),
                new Book("title five", "author five", "accounting"),
                new Book("title six", "author six", "philosophy")
        ));
    }

    @GetMapping("/api/books")
    public List<Book> getBooks() {
        return this.books;
    }

    @GetMapping("/api/books/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}
