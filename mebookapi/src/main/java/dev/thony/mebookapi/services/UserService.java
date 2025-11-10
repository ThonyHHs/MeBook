package dev.thony.mebookapi.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.thony.mebookapi.models.BookshelfModel;
import dev.thony.mebookapi.models.UserModel;
import dev.thony.mebookapi.repositories.BookshelfRepository;
import dev.thony.mebookapi.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private BookshelfRepository bookshelfRepository;

    public UserService(UserRepository userRepository, BookshelfRepository bookshelfRepository) {
        this.userRepository = userRepository;
        this.bookshelfRepository = bookshelfRepository;
    }

    public List<UserModel> getAll() {
        return userRepository.findAll();
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
        UserModel user = getById(id);
        BookshelfModel bookshelf = user.getBookshelf();
        if (bookshelf != null) {
            bookshelfRepository.delete(bookshelf);
        }
        userRepository.delete(user);
    }
    
}
