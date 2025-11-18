package dev.thony.mebookapi.models.DTOs;

import dev.thony.mebookapi.models.GenreModel;

public class GenreMapper {
    public static GenreDTO toDto(GenreModel genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        return genreDTO;
    }
}
