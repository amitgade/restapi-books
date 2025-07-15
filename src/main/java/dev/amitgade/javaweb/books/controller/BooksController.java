package dev.amitgade.javaweb.books.controller;

import dev.amitgade.javaweb.books.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {
    private final List<Book> books = new ArrayList<>();

    public BooksController() {
        initializeBooks();
    }

    public void initializeBooks() {
        books.addAll(List.of(
                new Book(1, "Cracking Coding Interview", "Gayle M.", "Computer Science", 5),
                new Book(2, "Java 8 And Beyond", "Bob M.", "Computer Science", 5),
                new Book(3, "Why 1+1 Rocks", "Ramesh C.", "Math", 5),
                new Book(4, "Humans And Cosmos", "Alex J.", "Science", 2),
                new Book(5, "Clash of Titans", "Julia R", "History", 3),
                new Book(6, "Anything but Numbers", "David A.", "Math", 1)
        ));
    }

    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String category) {
        if (category == null) {
            return books;
        }
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public void createBook(@RequestBody Book newBook) {
        boolean isNewBook = books.stream()
                .noneMatch(book -> book.getTitle().equalsIgnoreCase(newBook.getTitle()));
        if (isNewBook) {
            books.add(newBook);
        }
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable long id, @RequestBody Book updateBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.set(i, updateBook);
            }
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        books.removeIf(book -> book.getId() == id);
    }
}
