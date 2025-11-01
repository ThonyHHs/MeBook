package controllers;

import java.util.List;
import java.util.UUID;

import models.BookshelfModel;
import services.BookshelfService;

public class BookshelfController {

    private BookshelfService bookshelfService;

    public BookshelfController(BookshelfService bookshelfService) {
        this.bookshelfService = bookshelfService;
    }

    public List<BookshelfModel> getAll() {
        return bookshelfService.getAll();
    }

    public BookshelfModel getById(UUID id) {
        return bookshelfService.getById(id);
    }

    public BookshelfModel createBookshelf(UUID userId, boolean visibility) {
        return bookshelfService.createBookshelf(userId, visibility);
    }

    public BookshelfModel addBook(UUID bookshelfId, UUID bookId) {
        return bookshelfService.addBook(bookshelfId, bookId);
    }

    public void delete(UUID id) {
        bookshelfService.delete(id);
    }

    public void deleteBook(UUID bookshelfId, UUID bookId) {
        bookshelfService.deleteBook(bookshelfId, bookId);
    }

    public BookshelfModel setVisibility(UUID id) {
        return bookshelfService.setVisibility(id);
    }
}
