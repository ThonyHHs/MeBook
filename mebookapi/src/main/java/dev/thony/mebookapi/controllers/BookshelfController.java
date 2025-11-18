package dev.thony.mebookapi.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.thony.mebookapi.models.DTOs.BookshelfDTO;
import dev.thony.mebookapi.models.DTOs.BookshelfMapper;
import dev.thony.mebookapi.services.BookshelfService;

@RestController
@RequestMapping("/v1/bookshelves")
public class BookshelfController {

    private BookshelfService bookshelfService;

    public BookshelfController(BookshelfService bookshelfService) {
        this.bookshelfService = bookshelfService;
    }

    @GetMapping()
    public List<BookshelfDTO> getAll() {
        return bookshelfService.getAll().stream().map(BookshelfMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{bookshelfId}")
    public BookshelfDTO getById(@PathVariable UUID bookshelfId) {
        return BookshelfMapper.toDto(bookshelfService.getById(bookshelfId));
    }

}
