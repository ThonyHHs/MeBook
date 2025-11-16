package dev.thony.mebookapi.models.DTOs;

import dev.thony.mebookapi.models.BookModel;

public class BookMapper {
    public static BookDTO toDto(BookModel book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setReleaseDate(book.getReleaseDate());
        bookDTO.setGenres(book.getGenres());

        return bookDTO;
    }
}
