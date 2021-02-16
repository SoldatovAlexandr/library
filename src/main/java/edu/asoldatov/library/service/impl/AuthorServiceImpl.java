package edu.asoldatov.library.service.impl;

import edu.asoldatov.library.dto.IdDto;
import edu.asoldatov.library.dto.mapper.AuthorDtoMapper;
import edu.asoldatov.library.dto.request.AuthorDtoRequest;
import edu.asoldatov.library.dto.response.AuthorDtoResponse;
import edu.asoldatov.library.dto.response.EmptyDtoResponse;
import edu.asoldatov.library.erroritem.code.ServerErrorCodeWithField;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.model.Author;
import edu.asoldatov.library.model.Book;
import edu.asoldatov.library.repository.AuthorRepository;
import edu.asoldatov.library.repository.BookRepository;
import edu.asoldatov.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final static AuthorDtoMapper AUTHOR_DTO_MAPPER = AuthorDtoMapper.INSTANCE;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public AuthorDtoResponse createAuthor(AuthorDtoRequest authorDtoRequest) {
        Author author = AUTHOR_DTO_MAPPER.toAuthor(authorDtoRequest);

        author.setBooks(new ArrayList<>());

        authorRepository.save(author);

        return AUTHOR_DTO_MAPPER.toAuthorDtoResponse(author);
    }

    @Override
    public AuthorDtoResponse updateAuthor(AuthorDtoRequest authorDtoRequest, long authorId) throws ServerException {
        Author author = getAuthorById(authorId);

        String firstName = authorDtoRequest.getFirstName();

        if (firstName != null) {
            author.setFirstName(firstName);
        }

        String lastName = authorDtoRequest.getLastName();

        if (lastName != null) {
            author.setLastName(lastName);
        }

        String patronymic = authorDtoRequest.getPatronymic();

        if (patronymic != null) {
            author.setPatronymic(patronymic);
        }

        Integer yearOfBirth = authorDtoRequest.getYearOfBirth();

        if (yearOfBirth != null) {
            author.setYearOfBirth(yearOfBirth);
        }

        String biography = authorDtoRequest.getBiography();

        if (biography != null) {
            author.setBiography(biography);
        }

        authorRepository.save(author);

        return AUTHOR_DTO_MAPPER.toAuthorDtoResponse(author);
    }


    @Override
    public EmptyDtoResponse addBookToAuthor(long authorId, IdDto idDto) throws ServerException {
        Author author = getAuthorById(authorId);

        Book book = getBookById(idDto.getId());

        author.getBooks().add(book);

        authorRepository.save(author);

        return new EmptyDtoResponse();
    }


    @Override
    public EmptyDtoResponse deleteBookFromAuthor(long authorId, IdDto idDto) throws ServerException {
        Author author = getAuthorById(authorId);

        Book book = getBookById(idDto.getId());

        author.getBooks().remove(book);

        authorRepository.save(author);

        return new EmptyDtoResponse();
    }

    @Override
    public List<AuthorDtoResponse> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();

        return AUTHOR_DTO_MAPPER.toAuthorDtoResponses(authors);
    }

    @Override
    public AuthorDtoResponse getAuthor(long authorId) throws ServerException {
        Author author = getAuthorById(authorId);

        return AUTHOR_DTO_MAPPER.toAuthorDtoResponse(author);
    }

    private Author getAuthorById(long authorId) throws ServerException {
        return authorRepository.findById(authorId).orElseThrow(() -> new ServerException(ServerErrorCodeWithField.WRONG_AUTHOR_ID));
    }

    private Book getBookById(Long bookId) throws ServerException {
        return bookRepository.findById(bookId).orElseThrow(() -> new ServerException(ServerErrorCodeWithField.WRONG_BOOK_ID));
    }
}
