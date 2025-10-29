package services;

import java.util.List;
import java.util.UUID;

import models.UserModel;
import repositories.UserRepository;

public class UserService {
    
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> getAll() {
        return userRepository.getAll();
    }

    public UserModel getById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found!"));
    }

    public UserModel save(UserModel user) {
        return userRepository.save(user);
    }

    public UserModel update(UUID id, UserModel updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setBirthDate(updatedUser.getBirthDate());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User Not Found!"));
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
