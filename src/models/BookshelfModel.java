package models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Interfaces.Identification;

public class BookshelfModel implements Identification<UUID> {

    private UUID id;
    private UUID userId;
    private boolean visibility;
    private List<UUID> bookList = new ArrayList<>();

    public BookshelfModel(UUID userId, boolean visibility) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.visibility = visibility;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public List<UUID> getBookList() {
        return bookList;
    }

    public void setBookList(List<UUID> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return String.format("""
                {
                    id: %s,
                    userId: %s,
                    bookList: %s
                }""", id, userId, bookList);
    }
}
