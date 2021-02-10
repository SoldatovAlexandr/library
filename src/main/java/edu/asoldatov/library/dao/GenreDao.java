package edu.asoldatov.library.dao;

import edu.asoldatov.library.model.Genre;

import java.util.List;

public interface GenreDao {
    Genre getById(Long genreId);

    void update(Genre genre);

    void delete(Genre genre);

    void insert(Genre genre);

    List<Genre> getAll();
}
