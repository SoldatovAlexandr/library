package edu.asoldatov.library.service;

import edu.asoldatov.library.dao.BookDao;
import edu.asoldatov.library.dao.GenreDao;
import edu.asoldatov.library.erroritem.code.ServerErrorCodeWithField;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.model.Book;
import edu.asoldatov.library.model.Genre;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {NestedRuntimeException.class, ServerException.class})
public abstract class ServiceBase {
    protected final BookDao bookDao;
    protected final GenreDao genreDao;

    protected ServiceBase(BookDao bookDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.genreDao = genreDao;
    }

    protected Book getBookById(long bookId) throws ServerException {
        return bookDao.getById(bookId).orElseThrow(() -> new ServerException(ServerErrorCodeWithField.WRONG_BOOK_ID));
    }

    protected Genre getGenreById(Long genreId) throws ServerException {
        Genre genre = genreDao.getById(genreId);

        if (genre == null) {
            throw new ServerException(ServerErrorCodeWithField.WRONG_GENRE_ID);
        }

        return genre;
    }

}
