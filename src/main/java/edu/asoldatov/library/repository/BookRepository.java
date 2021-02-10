package edu.asoldatov.library.repository;

import edu.asoldatov.library.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}
