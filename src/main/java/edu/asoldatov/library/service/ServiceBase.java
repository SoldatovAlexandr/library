package edu.asoldatov.library.service;

import edu.asoldatov.library.dao.AuthorDao;
import edu.asoldatov.library.dao.BookDao;
import edu.asoldatov.library.dao.GenreDao;
import edu.asoldatov.library.dao.UserDao;
import edu.asoldatov.library.erroritem.code.ServerErrorCodeWithField;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.model.Author;
import edu.asoldatov.library.model.Book;
import edu.asoldatov.library.model.Genre;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public abstract class ServiceBase {
    protected final BookDao bookDao;
    protected final GenreDao genreDao;
    protected final UserDao userDao;
    protected final AuthorDao authorDao;

    protected ServiceBase(BookDao bookDao, GenreDao genreDao, UserDao userDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.genreDao = genreDao;
        this.userDao = userDao;
        this.authorDao = authorDao;
    }

    protected Book getBookById(long bookId) throws ServerException {
        return bookDao.getById(bookId).orElseThrow(() -> new ServerException(ServerErrorCodeWithField.WRONG_BOOK_ID));
    }

    protected Genre getGenreById(Long genreId) throws ServerException {
        return genreDao.getById(genreId).orElseThrow(() -> new ServerException(ServerErrorCodeWithField.WRONG_GENRE_ID));
    }

    protected Author getAuthorById(Long authorId) throws ServerException {
        return authorDao.getById(authorId).orElseThrow(() -> new ServerException(ServerErrorCodeWithField.WRONG_AUTHOR_ID));
    }
}
