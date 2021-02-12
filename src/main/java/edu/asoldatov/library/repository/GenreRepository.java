package edu.asoldatov.library.repository;

import edu.asoldatov.library.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}
