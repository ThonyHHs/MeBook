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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookshelf")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookshelfModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    private boolean visibility;

    @ManyToMany
    @JoinTable(name = "bookshelf_library", joinColumns = @JoinColumn(name = "bookshelf_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<BookModel> bookList;

}
