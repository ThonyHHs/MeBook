package controllers;

import java.util.List;
import java.util.UUID;

import models.BookshelfModel;
import models.UserModel;
import services.BookshelfService;
import services.UserService;

public class UserController {

    private UserService userService;
    private BookshelfService bookshelfService;

    public UserController(UserService userService, BookshelfService bookshelfService) {
        this.userService = userService;
        this.bookshelfService = bookshelfService;
    }

    public List<UserModel> getAll() {
        return userService.getAll();
    }

    public UserModel getById(UUID id) {
        return userService.getById(id);
    }

    public UserModel save(UserModel user) {
        return userService.save(user);
    }

    public UserModel update(UUID id, UserModel updatedUser) {
        return userService.update(id, updatedUser);
    }

    public void delete(UUID id) {
        bookshelfService.delete(userService.getById(id).getBookshelfId());
        userService.delete(id);
    }

    public BookshelfModel createBookshelf(UUID id, boolean visibility) {
        return bookshelfService.createBookshelf(id, visibility);
    }
}
