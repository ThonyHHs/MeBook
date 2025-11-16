package dev.thony.mebookapi.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.thony.mebookapi.models.BookModel;
import dev.thony.mebookapi.models.DTOs.BookDTO;
import dev.thony.mebookapi.models.DTOs.BookMapper;
import dev.thony.mebookapi.services.BookService;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getAll() {
        return bookService.getAll().stream().map(BookMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{bookId}")
    public BookDTO getById(@PathVariable UUID bookId) {
        return BookMapper.toDto(bookService.getById(bookId));
    }

    @PostMapping()
    public BookDTO save(@RequestBody BookModel book) {
        return BookMapper.toDto(bookService.save(book));
    }

    @PutMapping("/{bookId}")
    public BookDTO update(@PathVariable UUID bookId, @RequestBody BookModel updatedBook) {
        return BookMapper.toDto(bookService.update(bookId, updatedBook));
    }

    @DeleteMapping("/{bookId}")
    public void delete(@PathVariable UUID bookId) {
        bookService.delete(bookId);
    }

}
