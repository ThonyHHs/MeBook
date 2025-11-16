package dev.thony.mebookapi.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.thony.mebookapi.models.BookModel;
import dev.thony.mebookapi.models.BookshelfModel;
import dev.thony.mebookapi.models.UserModel;
import dev.thony.mebookapi.repositories.BookshelfRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookshelfService {

    private BookshelfRepository bookshelfRepository;
    private BookService bookService;
    private UserService userService;

    public BookshelfService(BookshelfRepository bookshelfRepository, BookService bookService, UserService userService) {
        this.bookshelfRepository = bookshelfRepository;
        this.bookService = bookService;
        this.userService = userService;
    }

    public List<BookshelfModel> getAll() {
        return bookshelfRepository.findAll().stream()
                .filter(bookshelf -> bookshelf.isVisibility())
                .collect(Collectors.toList());
    }

    public BookshelfModel getById(UUID id) {
        BookshelfModel bookshelf = bookshelfRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bookshelf Not Found!"));

        if (bookshelf.isVisibility() == false) {
            throw new RuntimeException("This bookshelf is not public");
        }
        return bookshelf;
    }

    public BookshelfModel getByUserId(UUID userId) {
        UserModel user = userService.getById(userId);
        BookshelfModel bookshelf = user.getBookshelf();

        if (bookshelf == null) {
            throw new RuntimeException("User does not have bookshelf!");
        }
        if (bookshelf.isVisibility() == false) {
            throw new RuntimeException("This bookshelf is not public!");
        }
        return bookshelf;
    }

    public BookshelfModel createBookshelf(UUID userId, boolean visibility) {
        UserModel user = userService.getById(userId);
        BookshelfModel bookshelf = user.getBookshelf();

        if (bookshelf != null) {
            return bookshelf;
        }

        bookshelf = new BookshelfModel();
        bookshelf.setUser(user);
        bookshelf.setVisibility(visibility);
        bookshelfRepository.save(bookshelf);

        return bookshelf;
    }

    public BookshelfModel addBook(UUID userId, UUID bookId) {
        UserModel user = userService.getById(userId);
        BookshelfModel bookshelf = user.getBookshelf();

        if (bookshelf == null) {
            throw new RuntimeException("User does not have a bookshelf!");
        }

        bookshelf.getBookList().add(bookService.getById(bookId));
        return bookshelfRepository.save(bookshelf);
    }

    public BookshelfModel update(UUID userId, BookshelfModel newBookshelf) {
        UserModel user = userService.getById(userId);
        BookshelfModel bookshelf = user.getBookshelf();

        if (bookshelf == null) {
            throw new RuntimeException("User does not have a bookshelf!");
        }

        bookshelf.setVisibility(newBookshelf.isVisibility());
        return bookshelfRepository.save(bookshelf);
    }

    public void delete(UUID userId) {
        UserModel user = userService.getById(userId);
        BookshelfModel bookshelf = user.getBookshelf();

        if (bookshelf == null) {
            throw new RuntimeException("User does not have a bookshelf");
        }

        user.setBookshelf(null);
        userService.update(userId, user);
        bookshelfRepository.delete(bookshelf);
    }

    public void deleteBook(UUID userId, UUID bookId) {
        UserModel user = userService.getById(userId);
        BookshelfModel bookshelf = user.getBookshelf();

        if (user.getBookshelf() == null) {
            throw new RuntimeException("User does not have a bookshelf");
        }

        BookModel bookToDelete = bookService.getById(bookId);
        bookshelf.getBookList().remove(bookToDelete);
        bookshelfRepository.save(bookshelf);
    }
}
