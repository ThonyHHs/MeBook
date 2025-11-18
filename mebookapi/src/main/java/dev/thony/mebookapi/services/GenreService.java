package dev.thony.mebookapi.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.thony.mebookapi.models.BookModel;
import dev.thony.mebookapi.models.GenreModel;
import dev.thony.mebookapi.repositories.GenreRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class GenreService {
    private GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreModel> getAll() {
        return genreRepository.findAll();
    }

    public GenreModel getById(UUID id) {
        return genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre Not Found!"));
    }

    public GenreModel save(GenreModel genre) {
        return genreRepository.save(genre);
    }

    public GenreModel update(UUID id, GenreModel updatedGenre) {
        return genreRepository.findById(id).map(existingGenre -> {
            existingGenre.setName(updatedGenre.getName());
            return genreRepository.save(existingGenre);
        }).orElseThrow(() -> new RuntimeException("Book Not Found!"));
    }

    public void delete(UUID id) {
        GenreModel genre = getById(id);
        for (BookModel book : genre.getBooks()) {
            book.getGenreList().remove(genre);
        }

        genreRepository.delete(genre);
    }
}
