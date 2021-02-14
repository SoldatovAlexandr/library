package edu.asoldatov.library.dao;

import edu.asoldatov.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void insert(Book book);

    Optional<Book> getById(long bookId);

    void update(Book book);

    List<Book> getAllBook();
}
