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

import dev.thony.mebookapi.models.BookshelfModel;
import dev.thony.mebookapi.models.UserModel;
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
    public List<UserModel> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public UserModel getById(@PathVariable UUID userId) {
        return userService.getById(userId);
    }

    @PostMapping
    public UserModel save(@RequestBody UserModel user) {
        return userService.save(user);
    }

    @PutMapping("/{userId}")
    public UserModel update(@PathVariable UUID userId, @RequestBody UserModel updatedUser) {
        return userService.update(userId, updatedUser);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable UUID userId) {
        bookshelfService.delete(userId);
        userService.delete(userId);
    }

    public BookshelfModel createBookshelf(UUID userId, boolean visibility) {
        return bookshelfService.createBookshelf(userId, visibility);
    }
}
