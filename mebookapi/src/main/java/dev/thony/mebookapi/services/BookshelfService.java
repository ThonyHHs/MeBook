package dev.thony.mebookapi.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.thony.mebookapi.models.BookModel;
import dev.thony.mebookapi.models.BookshelfModel;
import dev.thony.mebookapi.models.UserModel;
import dev.thony.mebookapi.repositories.BookshelfRepository;

@Service
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
        return bookshelfRepository.findAll();
    }

    public BookshelfModel getById(UUID id) {
        return bookshelfRepository.findById(id).orElseThrow(() -> new RuntimeException("Bookshelf Not Found!"));
    }

    public BookshelfModel createBookshelf(UUID userId, boolean visibility) {
        UserModel user = userService.getById(userId);
        BookshelfModel bookshelf = user.getBookshelf();
        
        if (bookshelf != null) {
            return bookshelf;
        }

        bookshelf = new BookshelfModel(user, visibility);
        bookshelfRepository.save(bookshelf);
        user.setBookshelf(bookshelf);
        userService.update(userId, user);

        return bookshelf;
    }

    public BookshelfModel addBook(UUID userId, UUID bookId) {
        UserModel user = userService.getById(userId);
        if (user.getBookshelf() != null) {
            BookshelfModel bookshelf = user.getBookshelf();
            Set<BookModel> updatedBookList = bookshelf.getBookList();
            updatedBookList.add(bookService.getById(bookId));
            bookshelf.setBookList(updatedBookList);
            return bookshelfRepository.save(bookshelf);
        } else {
            return createBookshelf(userId, true);
        }
    }

    public void delete(UUID userId) {
        bookshelfRepository.delete(userService.getById(userId).getBookshelf());
    }

    public void deleteBook(UUID userId, UUID bookId) {
        UserModel user = userService.getById(userId);
        if (user.getBookshelf() != null) {
            BookshelfModel bookshelf = new BookshelfModel();
            Set<BookModel> updatedBookList = bookshelf.getBookList();
            updatedBookList.remove(bookService.getById(bookId));
            
        } else {
            throw new RuntimeException("User does not have a bookshelf");
        }
    }

    public BookshelfModel setVisibility(UUID userId) {
        BookshelfModel bookshelf = userService.getById(userId).getBookshelf();

        if (bookshelf.isVisibility()) {
            bookshelf.setVisibility(false);
        } else {
            bookshelf.setVisibility(true);
        }

        return bookshelfRepository.save(bookshelf);
    }
}
