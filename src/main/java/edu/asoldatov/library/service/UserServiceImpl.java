package edu.asoldatov.library.service;

import edu.asoldatov.library.dao.AuthorDao;
import edu.asoldatov.library.dao.BookDao;
import edu.asoldatov.library.dao.GenreDao;
import edu.asoldatov.library.dao.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceBase implements UserService {

    protected UserServiceImpl(BookDao bookDao, GenreDao genreDao, UserDao userDao, AuthorDao authorDao) {
        super(bookDao, genreDao, userDao, authorDao);
    }
}
