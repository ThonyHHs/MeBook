package controllers;

import java.util.List;
import java.util.UUID;

import models.BookModel;
import services.BookService;

public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public List<BookModel> getAll() {
        return bookService.getAll();
    }

    public BookModel getById(UUID id) {
        return bookService.getById(id);
    }

    public BookModel save(BookModel book) {
        return bookService.save(book);
    }

    public BookModel update(UUID id, BookModel updatedBook) {
        return bookService.update(id, updatedBook);
    }

    public void delete(UUID id) {
        bookService.delete(id);
    }
}
