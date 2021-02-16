package edu.asoldatov.library.service.impl;

import edu.asoldatov.library.dto.IdDto;
import edu.asoldatov.library.dto.mapper.BookDtoMapper;
import edu.asoldatov.library.dto.request.BookDtoRequest;
import edu.asoldatov.library.dto.response.BookDtoResponse;
import edu.asoldatov.library.erroritem.code.ServerErrorCodeWithField;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.model.Author;
import edu.asoldatov.library.model.Book;
import edu.asoldatov.library.model.Genre;
import edu.asoldatov.library.model.User;
import edu.asoldatov.library.repository.AuthorRepository;
import edu.asoldatov.library.repository.BookRepository;
import edu.asoldatov.library.repository.GenreRepository;
import edu.asoldatov.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final static BookDtoMapper BOOK_DTO_MAPPER = BookDtoMapper.INSTANCE;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public BookDtoResponse createBook(BookDtoRequest bookDtoRequest) throws ServerException {
        Book book = BOOK_DTO_MAPPER.toBook(bookDtoRequest);

        long genreId = bookDtoRequest.getGenreId();

        Genre genre = getGenreById(genreId);

        book.setGenres(Collections.singleton(genre));

        bookRepository.save(book);

        return BOOK_DTO_MAPPER.toBookDtoResponse(book);
    }

    @Override
    public BookDtoResponse updateBook(BookDtoRequest bookDtoRequest, long bookId) throws ServerException {
        Book book = getBookById(bookId);

        book.setName(bookDtoRequest.getName());

        book.setYearOfPublishing(bookDtoRequest.getYearOfPublishing());

        Genre genre = getGenreById(bookDtoRequest.getGenreId());

        book.getGenres().add(genre);

        bookRepository.save(book);

        return BOOK_DTO_MAPPER.toBookDtoResponse(book);
    }


    @Override
    public BookDtoResponse takeBook(long bookId, User user) throws ServerException {
        Book book = getBookById(bookId);

        if (book.getUser() != null) {
            throw new ServerException(ServerErrorCodeWithField.BOOK_TAKEN);
        }

        book.setUser(user);

        bookRepository.save(book);

        return BOOK_DTO_MAPPER.toBookDtoResponse(book);
    }

    @Override
    public BookDtoResponse returnBook(long bookId, User user) throws ServerException {
        Book book = getBookById(bookId);

        User owner = book.getUser();

        if (!user.equals(owner)) {
            throw new ServerException(ServerErrorCodeWithField.NO_PERMISSION);
        }

        book.setUser(null);

        bookRepository.save(book);

        return BOOK_DTO_MAPPER.toBookDtoResponse(book);
    }

    @Override
    public List<BookDtoResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        return BOOK_DTO_MAPPER.toBooks(books);
    }

    @Override
    public BookDtoResponse getBook(long bookId) throws ServerException {
        Book book = getBookById(bookId);

        return BOOK_DTO_MAPPER.toBookDtoResponse(book);
    }

    @Override
    public BookDtoResponse addAuthor(IdDto idDto, long bookId) throws ServerException {

        Book book = getBookById(bookId);

        Long authorId = idDto.getId();

        Author author = getAuthorById(authorId);

        book.getAuthors().add(author);

        bookRepository.save(book);

        return BOOK_DTO_MAPPER.toBookDtoResponse(book);
    }


    @Override
    public BookDtoResponse deleteAuthor(long authorId, long bookId) throws ServerException {
        Book book = getBookById(bookId);

        Author author = getAuthorById(authorId);

        book.getAuthors().remove(author);

        bookRepository.save(book);

        return BOOK_DTO_MAPPER.toBookDtoResponse(book);
    }

    private Genre getGenreById(long genreId) throws ServerException {
        return genreRepository.findById(genreId).orElseThrow(() -> new ServerException(ServerErrorCodeWithField.WRONG_GENRE_ID));
    }

    private Book getBookById(long bookId) throws ServerException {
        return bookRepository.findById(bookId).orElseThrow(() -> new ServerException(ServerErrorCodeWithField.WRONG_BOOK_ID));
    }

    private Author getAuthorById(Long authorId) throws ServerException {
        return authorRepository.findById(authorId).orElseThrow(() -> new ServerException(ServerErrorCodeWithField.WRONG_AUTHOR_ID));
    }
}
