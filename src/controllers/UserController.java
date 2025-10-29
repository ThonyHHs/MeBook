package controllers;

import java.util.List;
import java.util.UUID;

import models.UserModel;
import services.UserService;

public class UserController {
    
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    public List<UserModel> getAll() {
        return service.getAll();
    }

    public UserModel getById(UUID id) {
        return service.getById(id);
    }

    public UserModel save(UserModel user) {
        return service.save(user);
    }

    public UserModel update(UUID id, UserModel updatedUser) {
        return service.update(id, updatedUser);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
