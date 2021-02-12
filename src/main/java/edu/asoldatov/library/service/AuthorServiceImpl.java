package edu.asoldatov.library.service;

import edu.asoldatov.library.dao.AuthorDao;
import edu.asoldatov.library.dao.BookDao;
import edu.asoldatov.library.dao.GenreDao;
import edu.asoldatov.library.dao.UserDao;
import edu.asoldatov.library.dto.mapper.AuthorDtoMapper;
import edu.asoldatov.library.dto.request.AddBookToAuthorDtoRequest;
import edu.asoldatov.library.dto.request.CreateAuthorDtoRequest;
import edu.asoldatov.library.dto.request.DeleteBookFromAuthorDtoRequest;
import edu.asoldatov.library.dto.request.UpdateAuthorDtoRequest;
import edu.asoldatov.library.dto.response.AuthorDtoResponse;
import edu.asoldatov.library.dto.response.EmptyDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.model.Author;
import edu.asoldatov.library.model.Book;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl extends ServiceBase implements AuthorService {
    protected AuthorServiceImpl(BookDao bookDao, GenreDao genreDao, UserDao userDao, AuthorDao authorDao) {
        super(bookDao, genreDao, userDao, authorDao);
    }

    @Override
    public AuthorDtoResponse createAuthor(CreateAuthorDtoRequest request) {
        Author author = AuthorDtoMapper.INSTANCE.toAuthor(request);

        authorDao.insert(author);

        return AuthorDtoMapper.INSTANCE.toAuthorDtoResponse(author);
    }

    @Override
    public AuthorDtoResponse updateAuthor(UpdateAuthorDtoRequest request, long authorId) throws ServerException {
        Author author = getAuthorById(authorId);

        String firstName = request.getFirstName();

        if (firstName != null) {
            author.setFirstName(firstName);
        }

        String lastName = request.getLastName();

        if (lastName != null) {
            author.setLastName(lastName);
        }

        String patronymic = request.getPatronymic();

        if (patronymic != null) {
            author.setPatronymic(patronymic);
        }

        Integer yearOfBirth = request.getYearOfBirth();

        if (yearOfBirth != null) {
            author.setYearOfBirth(yearOfBirth);
        }

        String biography = request.getBiography();

        if (biography != null) {
            author.setBiography(biography);
        }

        authorDao.update(author);

        return AuthorDtoMapper.INSTANCE.toAuthorDtoResponse(author);
    }

    @Override
    public EmptyDtoResponse addBookToAuthor(long authorId, AddBookToAuthorDtoRequest request) throws ServerException {
        Author author = getAuthorById(authorId);

        Book book = getBookById(request.getBookId());

        author.getBooks().add(book);

        authorDao.update(author);

        return new EmptyDtoResponse();
    }

    @Override
    public EmptyDtoResponse deleteBookFromAuthor(long authorId, DeleteBookFromAuthorDtoRequest request) throws ServerException {
        Author author = getAuthorById(authorId);

        Book book = getBookById(request.getBookId());

        author.getBooks().remove(book);

        authorDao.update(author);

        return new EmptyDtoResponse();
    }
}
