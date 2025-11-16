package dev.thony.mebookapi.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.thony.mebookapi.models.BookModel;
import dev.thony.mebookapi.models.BookshelfModel;
import dev.thony.mebookapi.repositories.BookRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookModel> getAll() {
        return bookRepository.findAll();
    }

    public BookModel getById(UUID id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found!"));
    }

    public BookModel save(BookModel book) {
        return bookRepository.save(book);
    }

    public BookModel update(UUID id, BookModel updatedBook) {
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setDescription(updatedBook.getDescription());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setReleaseDate(updatedBook.getReleaseDate());
            existingBook.setGenres(updatedBook.getGenres());
            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Book Not Found!"));
    }

    public void delete(UUID id) {
        BookModel book = getById(id);
        for (BookshelfModel bookshelf : book.getBookshelves()) {
            bookshelf.getBookList().remove(book);
        }

        bookRepository.delete(book);
    }

}
