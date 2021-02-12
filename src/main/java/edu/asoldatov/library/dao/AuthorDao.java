package edu.asoldatov.library.dao;

import edu.asoldatov.library.model.Author;

import java.util.Optional;

public interface AuthorDao {
    Optional<Author> getById(Long authorId);

    void insert(Author author);

    void update(Author author);
}
