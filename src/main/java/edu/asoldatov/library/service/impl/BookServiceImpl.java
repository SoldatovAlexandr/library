package edu.asoldatov.library.service.impl;

import edu.asoldatov.library.dto.mapper.BookDtoMapper;
import edu.asoldatov.library.dto.request.AddAuthorToBookDtoRequest;
import edu.asoldatov.library.dto.request.CreateBookDtoRequest;
import edu.asoldatov.library.dto.request.DeleteAuthorFromBookDtoRequest;
import edu.asoldatov.library.dto.request.UpdateBookDtoRequest;
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

    private final BookRepository bookRepository;

    private final GenreRepository genreRepository;

    private final AuthorRepository authorRepository;

    private final static BookDtoMapper BOOK_DTO_MAPPER = BookDtoMapper.INSTANCE;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public BookDtoResponse createBook(CreateBookDtoRequest request) throws ServerException {
        Book book = BOOK_DTO_MAPPER.toBook(request);

        long genreId = request.getGenreId();

        Genre genre = getGenreById(genreId);

        book.setGenres(Collections.singleton(genre));

        bookRepository.save(book);

        return BOOK_DTO_MAPPER.toBookDtoResponse(book);
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
    public BookDtoResponse addAuthor(AddAuthorToBookDtoRequest request, long bookId) throws ServerException {

        Book book = getBookById(bookId);

        Long authorId = request.getAuthorId();

        Author author = getAuthorById(authorId);

        book.getAuthors().add(author);

        bookRepository.save(book);

        return BOOK_DTO_MAPPER.toBookDtoResponse(book);
    }


    @Override
    public BookDtoResponse deleteAuthor(DeleteAuthorFromBookDtoRequest request, long bookId) throws ServerException {
        Book book = getBookById(bookId);

        Long authorId = request.getDeleteAuthorId();

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
