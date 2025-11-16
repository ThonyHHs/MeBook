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

import dev.thony.mebookapi.models.BookshelfModel;
import dev.thony.mebookapi.models.UserModel;
import dev.thony.mebookapi.models.DTOs.BookDTO;
import dev.thony.mebookapi.models.DTOs.BookMapper;
import dev.thony.mebookapi.models.DTOs.BookshelfDTO;
import dev.thony.mebookapi.models.DTOs.BookshelfMapper;
import dev.thony.mebookapi.models.DTOs.UserDTO;
import dev.thony.mebookapi.models.DTOs.UserMapper;
import dev.thony.mebookapi.services.BookshelfService;
import dev.thony.mebookapi.services.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private UserService userService;
    private BookshelfService bookshelfService;

    public UserController(UserService userService, BookshelfService bookshelfService) {
        this.userService = userService;
        this.bookshelfService = bookshelfService;
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAll().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public UserDTO getById(@PathVariable UUID userId) {
        return UserMapper.toDto(userService.getById(userId));
    }

    @PostMapping
    public UserDTO save(@RequestBody UserModel user) {
        return UserMapper.toDto(userService.save(user));
    }

    @PutMapping("/{userId}")
    public UserDTO update(@PathVariable UUID userId, @RequestBody UserModel updatedUser) {
        return UserMapper.toDto(userService.update(userId, updatedUser));
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable UUID userId) {
        userService.delete(userId);
    }

    @GetMapping("/{userId}/bookshelf")
    public BookshelfDTO getBookshelf(@PathVariable UUID userId) {
        return BookshelfMapper.toDto(bookshelfService.getByUserId(userId));
    }

    @PostMapping("/{userId}/bookshelf")
    public BookshelfDTO createBookshelf(@PathVariable UUID userId, @RequestBody BookshelfModel newBookshelf) {
        return BookshelfMapper.toDto(bookshelfService.createBookshelf(userId, newBookshelf.isVisibility()));
    }

    @PutMapping("/{userId}/bookshelf")
    public BookshelfDTO updateBookshelf(@PathVariable UUID userId, @RequestBody BookshelfModel newBookshelf) {
        return BookshelfMapper.toDto(bookshelfService.update(userId, newBookshelf));
    }

    @DeleteMapping("/{userId}/bookshelf")
    public void deleteBookshelf(@PathVariable UUID userId) {
        bookshelfService.delete(userId);
    }

    @GetMapping("/{userId}/bookshelf/books")
    public List<BookDTO> getBookshelfBooks(@PathVariable UUID userId) {
        return bookshelfService.getByUserId(userId).getBookList().stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/{userId}/bookshelf/books/{bookId}")
    public BookshelfDTO addBookToBookshelf(@PathVariable UUID userId, @PathVariable UUID bookId) {
        return BookshelfMapper.toDto(bookshelfService.addBook(userId, bookId));
    }

    @DeleteMapping("/{userId}/bookshelf/books/{bookId}")
    public void deleteBookFromBookshelf(@PathVariable UUID userId, @PathVariable UUID bookId) {
        bookshelfService.deleteBook(userId, bookId);
    }

}
