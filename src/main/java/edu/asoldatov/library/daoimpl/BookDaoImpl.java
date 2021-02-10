package edu.asoldatov.library.daoimpl;

import edu.asoldatov.library.dao.BookDao;
import edu.asoldatov.library.model.Book;
import edu.asoldatov.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    private final BookRepository bookRepository;

    @Autowired
    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void insert(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Optional<Book> getById(long bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public void update(Book book) {
        bookRepository.save(book);
    }
}
