package dev.thony.mebookapi.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.thony.mebookapi.models.BookModel;
import dev.thony.mebookapi.services.BookService;


@RestController
@RequestMapping("/v1/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookModel> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookModel getById(@PathVariable UUID id) {
        return bookService.getById(id);
    }

    @PostMapping()
    public BookModel save(@RequestBody BookModel book) {
        return bookService.save(book);
    }

    @PutMapping("/{id}")
    public BookModel update(@PathVariable UUID id, @RequestBody BookModel updatedBook) {
        return bookService.update(id, updatedBook);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        bookService.delete(id);
    }
}
