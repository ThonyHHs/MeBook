package dev.thony.mebookapi.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.thony.mebookapi.models.BookshelfModel;
import dev.thony.mebookapi.services.BookshelfService;

@RestController
@RequestMapping("/v1/bookshelves")
public class BookshelfController {

    private BookshelfService bookshelfService;

    public BookshelfController(BookshelfService bookshelfService) {
        this.bookshelfService = bookshelfService;
    }

    @GetMapping("/bookshelves")
    public List<BookshelfModel> getAll() {
        return bookshelfService.getAll();
    }

    @GetMapping("/bookshelves/{bookshelfId}")
    public BookshelfModel getById(@PathVariable UUID bookshelfId) {
        return bookshelfService.getById(bookshelfId);
    }

}
