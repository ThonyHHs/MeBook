package models;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import models.Interfaces.Identification;
import models.enums.GenreEnum;

public class BookModel implements Identification<UUID> {

    private UUID id;
    private String title;
    private String description;
    private String author;
    private LocalDate releaseDate;
    private List<GenreEnum> genres;

    public BookModel(String title, String description, String author, LocalDate releaseDate, List<GenreEnum> genres) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.author = author;
        this.releaseDate = releaseDate;
        this.genres = genres;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<GenreEnum> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreEnum> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return String.format("""
                {
                    id: %s
                    title: %s
                    description: %s
                    author: %s
                    releaseDate: %s
                    genres: %s
                }""", id, title, description, author, releaseDate, genres);
    }
}
