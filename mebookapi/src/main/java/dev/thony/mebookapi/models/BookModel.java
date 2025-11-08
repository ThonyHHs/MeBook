package dev.thony.mebookapi.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import dev.thony.mebookapi.models.enums.GenreEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class BookModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private String author;
    private LocalDate releaseDate;
    private List<GenreEnum> genres;

    @ManyToMany(mappedBy = "bookList")
    private Set<BookshelfModel> bookshelves = new HashSet<>();

    
    public BookModel() {}
    
    public BookModel(String title, String description, String author, LocalDate releaseDate, List<GenreEnum> genres) {
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

    public Set<BookshelfModel> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(Set<BookshelfModel> bookshelves) {
        this.bookshelves = bookshelves;
    }
}