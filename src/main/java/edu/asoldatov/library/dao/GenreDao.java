package edu.asoldatov.library.dao;

import edu.asoldatov.library.model.Genre;

import java.util.Optional;

public interface GenreDao {
    Optional<Genre> getById(Long genreId);

    void update(Genre genre);

    void delete(Genre genre);

    void insert(Genre genre);

    Iterable<Genre> getAll();
}
