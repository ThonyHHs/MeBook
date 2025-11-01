package services;

import java.util.List;
import java.util.UUID;

import models.BookModel;
import repositories.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookModel> getAll() {
        return bookRepository.getAll();
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
        bookRepository.deleteById(id);
    }
}
