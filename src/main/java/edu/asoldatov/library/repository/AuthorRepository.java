package edu.asoldatov.library.repository;

import edu.asoldatov.library.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
