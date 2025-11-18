package dev.thony.mebookapi.models.DTOs;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import dev.thony.mebookapi.models.GenreModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private UUID id;
    private String title;
    private String description;
    private String author;
    private LocalDate releaseDate;
    private Set<GenreModel> genreList;
}
