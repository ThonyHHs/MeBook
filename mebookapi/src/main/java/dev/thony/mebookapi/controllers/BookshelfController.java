package dev.thony.mebookapi.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.thony.mebookapi.models.BookshelfModel;
import dev.thony.mebookapi.services.BookshelfService;

@RestController
@RequestMapping("/v1")
public class BookshelfController {

    private BookshelfService bookshelfService;

    public BookshelfController(BookshelfService bookshelfService) {
        this.bookshelfService = bookshelfService;
    }

    @GetMapping("/bookshelves")
    public List<BookshelfModel> getAll() {
        return bookshelfService.getAll();
    }

    @GetMapping("/bookshelves/{id}")
    public BookshelfModel getById(@PathVariable UUID id) {
        return bookshelfService.getById(id);
    }

    @PostMapping("/users/{userId}/bookshelf")
    public BookshelfModel createBookshelf(@PathVariable UUID userId, @RequestParam boolean visibility) {
        return bookshelfService.createBookshelf(userId, visibility);
    }
    
    @DeleteMapping("/users/{id}/bookshelf")
    public void delete(@PathVariable UUID id) {
        bookshelfService.delete(id);
    }
    
    @DeleteMapping("/users/bookshelf/{bookshelfId}/{bookId}")
    public void deleteBook(@PathVariable UUID bookshelfId, @PathVariable UUID bookId) {
        bookshelfService.deleteBook(bookshelfId, bookId);
    }
    
    @PutMapping("/users/bookshelf/{bookshelfId}/{bookId}")
    public BookshelfModel addBook(@PathVariable UUID bookshelfId, @PathVariable UUID bookId) {
        return bookshelfService.addBook(bookshelfId, bookId);
    }

    @PutMapping("/users/{id}/bookshelf/visibility")
    public BookshelfModel setVisibility(UUID id) {
        return bookshelfService.setVisibility(id);
    }
}
