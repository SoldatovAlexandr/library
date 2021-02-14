package edu.asoldatov.library.service;

import edu.asoldatov.library.dao.*;
import edu.asoldatov.library.dto.mapper.BookDtoMapper;
import edu.asoldatov.library.dto.request.AddAuthorToBookDtoRequest;
import edu.asoldatov.library.dto.request.CreateBookDtoRequest;
import edu.asoldatov.library.dto.request.DeleteAuthorFromBookDtoRequest;
import edu.asoldatov.library.dto.request.UpdateBookDtoRequest;
import edu.asoldatov.library.dto.response.BookDtoResponse;
import edu.asoldatov.library.dto.response.UserDtoResponse;
import edu.asoldatov.library.erroritem.code.ServerErrorCodeWithField;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.model.Author;
import edu.asoldatov.library.model.Book;
import edu.asoldatov.library.model.Genre;
import edu.asoldatov.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl extends ServiceBase implements BookService {

    @Autowired
    public BookServiceImpl(BookDao bookDao, GenreDao genreDao, UserDao userDao, AuthorDao authorDao, RoleDao roleDao) {
        super(bookDao, genreDao, userDao, authorDao, roleDao);
    }

    @Override
    public BookDtoResponse createBook(CreateBookDtoRequest request) throws ServerException {
        Book book = BookDtoMapper.INSTANCE.toBook(request);

        long genreId = request.getGenreId();

        Genre genre = getGenreById(genreId);

        Set<Genre> genres = new HashSet<>();

        genres.add(genre);

        book.setGenres(genres);

        bookDao.insert(book);

        return BookDtoMapper.INSTANCE.toBookDtoResponse(book);
    }

    @Override
    public BookDtoResponse updateBook(UpdateBookDtoRequest request, long bookId) throws ServerException {
        Book book = getBookById(bookId);

        String name = request.getName();

        if (name != null) {
            book.setName(name);
        }

        Integer yearOfPublishing = request.getYearOfPublishing();

        if (yearOfPublishing != null) {
            book.setYearOfPublishing(yearOfPublishing);
        }

        Long genreId = request.getGenreId();

        if (genreId != null) {
            Genre genre = getGenreById(genreId);

            book.getGenres().add(genre);
        }

        bookDao.update(book);

        return BookDtoMapper.INSTANCE.toBookDtoResponse(book);
    }

    //TODO
    @Override
    public UserDtoResponse getBookOwner(long bookId) {
        return null;
    }

    @Override
    public BookDtoResponse takeBook(long bookId, User user) throws ServerException {
        Book book = getBookById(bookId);

        if (book.getUser() != null) {
            throw new ServerException(ServerErrorCodeWithField.BOOK_TAKEN);
        }

        book.setUser(user);

        bookDao.update(book);

        return BookDtoMapper.INSTANCE.toBookDtoResponse(book);
    }

    @Override
    public BookDtoResponse returnBook(long bookId, User user) throws ServerException {
        Book book = getBookById(bookId);

        User owner = book.getUser();

        if (!user.equals(owner)) {
            throw new ServerException(ServerErrorCodeWithField.NO_PERMISSION);
        }

        book.setUser(null);

        return BookDtoMapper.INSTANCE.toBookDtoResponse(book);
    }

    @Override
    public List<BookDtoResponse> getAllBooks() {
        List<Book> books = bookDao.getAllBook();

        return BookDtoMapper.INSTANCE.toBooks(books);
    }

    @Override
    public BookDtoResponse getBook(long bookId) throws ServerException {
        Book book = getBookById(bookId);

        return BookDtoMapper.INSTANCE.toBookDtoResponse(book);
    }

    @Override
    public BookDtoResponse addAuthor(AddAuthorToBookDtoRequest request, long bookId) throws ServerException {

        Book book = getBookById(bookId);

        Long authorId = request.getAuthorId();

        Author author = getAuthorById(authorId);

        book.getAuthors().add(author);

        bookDao.update(book);

        return BookDtoMapper.INSTANCE.toBookDtoResponse(book);
    }

    @Override
    public BookDtoResponse deleteAuthor(DeleteAuthorFromBookDtoRequest request, long bookId) throws ServerException {
        Book book = getBookById(bookId);

        Long authorId = request.getDeleteAuthorId();

        Author author = getAuthorById(authorId);

        book.getAuthors().remove(author);

        bookDao.update(book);

        return BookDtoMapper.INSTANCE.toBookDtoResponse(book);
    }
}
