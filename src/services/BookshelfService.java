package services;

import java.util.List;
import java.util.UUID;

import models.BookshelfModel;
import models.UserModel;
import repositories.BookshelfRepository;

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
        return bookshelfRepository.getAll();
    }

    public BookshelfModel getById(UUID id) {
        return bookshelfRepository.findById(id).orElseThrow(() -> new RuntimeException("Bookshelf Not Found!"));
    }

    public BookshelfModel createBookshelf(UUID userId, boolean visibility) {
        UserModel user = userService.getById(userId);

        if (user.getBookshelfId() != null) {
            return bookshelfRepository.findById(user.getBookshelfId())
                    .orElseThrow(() -> new RuntimeException("User already has an associated bookshelf that could not be found"));
        }

        BookshelfModel newBookshelf = bookshelfRepository.save(new BookshelfModel(userId, visibility));
        user.setBookshelfId(newBookshelf.getId());
        userService.update(userId, user);

        return newBookshelf;
    }

    public BookshelfModel addBook(UUID bookshelfId, UUID bookId) {
        return bookshelfRepository.findById(bookshelfId).map(bookshelf -> {
            bookService.getById(bookId);
            List<UUID> updatedBookList = bookshelf.getBookList();
            updatedBookList.add(bookId);
            bookshelf.setBookList(updatedBookList);
            return bookshelfRepository.save(bookshelf);
        }).orElseThrow(() -> new RuntimeException("Cannot add book to bookshelf"));
    }

    public void delete(UUID id) {
        bookshelfRepository.deleteById(id);
    }

    public void deleteBook(UUID bookshelfId, UUID bookId) {
        bookshelfRepository.findById(bookId).map(bookshelf -> {
            List<UUID> updatedBookList = bookshelf.getBookList();
            return updatedBookList.remove(bookId);
        }).orElseThrow(() -> new RuntimeException("Coudn't delete the book from bookshelf"));
    }

    public BookshelfModel setVisibility(UUID id) {
        BookshelfModel bookshelf = bookshelfRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bookshelf does not exist!"));

        if (bookshelf.isVisibility()) {
            bookshelf.setVisibility(false);
        } else {
            bookshelf.setVisibility(true);
        }

        return bookshelfRepository.save(bookshelf);
    }
}
