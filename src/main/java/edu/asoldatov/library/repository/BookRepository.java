package edu.asoldatov.library.repository;

import edu.asoldatov.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
