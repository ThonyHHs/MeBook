package dev.thony.mebookapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.thony.mebookapi.models.BookshelfModel;

@Repository
public interface BookshelfRepository extends JpaRepository<BookshelfModel, UUID> {

}
