package dev.thony.mebookapi.models;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookshelf")
public class BookshelfModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    private boolean visibility;

    @ManyToMany
    @JoinTable(name = "bookshelf_library", joinColumns = @JoinColumn(name = "bookshelf_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<BookModel> bookList;

    public BookshelfModel() {
    }

    public BookshelfModel(UserModel user, boolean visibility) {
        this.user = user;
        this.visibility = visibility;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public Set<BookModel> getBookList() {
        return bookList;
    }

    public void setBookList(Set<BookModel> bookList) {
        this.bookList = bookList;
    }
}
