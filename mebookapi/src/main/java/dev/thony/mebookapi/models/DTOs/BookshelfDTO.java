package dev.thony.mebookapi.models.DTOs;

import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookshelfDTO {
    private UUID id;
    private boolean visibility;
    private Set<BookDTO> bookList;
}
