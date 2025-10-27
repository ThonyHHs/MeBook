package services;

import java.util.List;
import java.util.UUID;

import models.UserModel;
import repositories.UserRepository;

public class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserModel> getAll() {
        return repository.getAll();
    }

    public UserModel getById(UUID id) {
        return repository.getById(id);
    }

    public UserModel save(UserModel user) {
        return repository.save(user);
    }

    public UserModel update(UUID id, UserModel updatedUser) {
        return repository.update(id, updatedUser);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
