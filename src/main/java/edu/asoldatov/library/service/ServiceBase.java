package edu.asoldatov.library.service;

import edu.asoldatov.library.dao.*;
import edu.asoldatov.library.erroritem.code.ServerErrorCodeWithField;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.model.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public abstract class ServiceBase {
    protected final BookDao bookDao;
    protected final GenreDao genreDao;
    protected final UserDao userDao;
    protected final AuthorDao authorDao;
    protected final RoleDao roleDao;

    private final static String USER_NOT_FOUND = "User not found";

    protected ServiceBase(BookDao bookDao, GenreDao genreDao, UserDao userDao, AuthorDao authorDao, RoleDao roleDao) {
        this.bookDao = bookDao;
        this.genreDao = genreDao;
        this.userDao = userDao;
        this.authorDao = authorDao;
        this.roleDao = roleDao;
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

    protected User getUserById(Long userId) throws ServerException {
        return userDao.getById(userId).orElseThrow(() -> new ServerException(ServerErrorCodeWithField.WRONG_USER_ID));
    }

    protected User getUserByName(String username) {
        return userDao.getByUsername(username).orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
    }

    protected boolean hasUsername(String username) {
        return userDao.getByUsername(username).isPresent();
    }

    protected Role getRoleByName(String name) throws ServerException {
        return roleDao.findByName(name).orElseThrow(() -> new ServerException(ServerErrorCodeWithField.EMPTY_DATABASE));
    }
}
