package dev.thony.mebookapi.models.DTOs;

import dev.thony.mebookapi.models.BookshelfModel;

public class BookshelfMapper {
    public static BookshelfDTO toDto(BookshelfModel bookshelf) {
        BookshelfDTO bookshelfDTO = new BookshelfDTO();
        bookshelfDTO.setId(bookshelf.getId());
        bookshelfDTO.setVisibility(bookshelf.isVisibility());
        bookshelfDTO.setBookList(bookshelf.getBookList());

        return bookshelfDTO;
    }
}
