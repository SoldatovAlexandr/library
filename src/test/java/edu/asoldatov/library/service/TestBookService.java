package edu.asoldatov.library.service;

import edu.asoldatov.library.dto.IdDto;
import edu.asoldatov.library.dto.request.BookDtoRequest;
import edu.asoldatov.library.dto.response.BookDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.model.Author;
import edu.asoldatov.library.model.Book;
import edu.asoldatov.library.model.Genre;
import edu.asoldatov.library.model.User;
import edu.asoldatov.library.repository.AuthorRepository;
import edu.asoldatov.library.repository.BookRepository;
import edu.asoldatov.library.repository.GenreRepository;
import edu.asoldatov.library.repository.UserRepository;
import edu.asoldatov.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TestBookService {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testCreateBook() throws ServerException {
        BookService bookService = new BookServiceImpl(bookRepository, genreRepository, authorRepository);

        Genre genre = new Genre(1L, "genre");

        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));

        BookDtoRequest bookDtoRequest = new BookDtoRequest("name", 2000, 1L);

        BookDtoResponse response = bookService.createBook(bookDtoRequest);

        Assertions.assertAll(
                () -> verify(bookRepository).save(any()),
                () -> Assertions.assertEquals("name", response.getName()),
                () -> Assertions.assertNull(response.getUser())
        );
    }

    @Test
    public void testUpdateBook() throws ServerException {
        BookService bookService = new BookServiceImpl(bookRepository, genreRepository, authorRepository);

        Genre genre = new Genre(1L, "genre");

        Book book = new Book(10L, "name", 2000, null, new HashSet<>(), new HashSet<>());

        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));

        when(bookRepository.findById(10L)).thenReturn(Optional.of(book));

        BookDtoRequest request = new BookDtoRequest("new name", 1999, 1L);

        BookDtoResponse response = bookService.updateBook(request, 10L);

        Assertions.assertAll(
                () -> verify(bookRepository).save(any()),
                () -> Assertions.assertEquals("new name", response.getName()),
                () -> Assertions.assertNull(response.getUser()),
                () -> Assertions.assertEquals(1999, response.getYearOfPublishing()),
                () -> Assertions.assertEquals(1, response.getGenres().size())
        );
    }

    @Test
    public void testGetAllBooks() {
        BookService bookService = new BookServiceImpl(bookRepository, genreRepository, authorRepository);

        List<Book> books = new ArrayList<>();

        books.add(new Book(10L, "name", 2000, null, new HashSet<>(), new HashSet<>()));

        books.add(new Book(11L, "name2", 2000, null, new HashSet<>(), new HashSet<>()));

        when(bookRepository.findAll()).thenReturn(books);

        List<BookDtoResponse> responses = bookService.getAllBooks();

        Assertions.assertAll(
                () -> verify(bookRepository).findAll(),
                () -> Assertions.assertEquals(2, responses.size())
        );
    }

    @Test
    public void testGetBook() throws ServerException {
        BookService bookService = new BookServiceImpl(bookRepository, genreRepository, authorRepository);

        Book book = new Book(10L, "name", 2000, null, new HashSet<>(), new HashSet<>());

        when(bookRepository.findById(10L)).thenReturn(Optional.of(book));

        BookDtoResponse expectedResponse = new BookDtoResponse(10L, "name", 2000, new ArrayList<>(), new ArrayList<>(), null);

        BookDtoResponse response = bookService.getBook(10L);

        Assertions.assertAll(
                () -> verify(bookRepository).findById(10L),
                () -> Assertions.assertEquals(expectedResponse, response)
        );
    }

    @Test
    public void testTakeBook() throws ServerException {
        BookService bookService = new BookServiceImpl(bookRepository, genreRepository, authorRepository);

        Book book = new Book(10L, "name", 2000, null, new HashSet<>(), new HashSet<>());

        User user = new User("username", "firstname", "lastname", "patronymic", 2000);

        user.setId(1L);

        when(bookRepository.findById(10L)).thenReturn(Optional.of(book));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        BookDtoResponse bookDtoResponse = bookService.takeBook(10L, user);

        Assertions.assertAll(
                () -> verify(bookRepository).save(any()),
                () -> Assertions.assertNotNull(bookDtoResponse.getUser())
        );
    }

    @Test
    public void testTakeBookFail() {
        BookService bookService = new BookServiceImpl(bookRepository, genreRepository, authorRepository);

        User user = new User("username", "firstname", "lastname", "patronymic", 2000);

        Book book = new Book(10L, "name", 2000, user, new HashSet<>(), new HashSet<>());

        user.setId(1L);

        when(bookRepository.findById(10L)).thenReturn(Optional.of(book));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Assertions.assertThrows(
                ServerException.class, () -> bookService.takeBook(10L, user)
        );
    }

    @Test
    public void testReturnBook() throws ServerException {
        BookService bookService = new BookServiceImpl(bookRepository, genreRepository, authorRepository);

        User user = new User("username", "firstname", "lastname", "patronymic", 2000);

        Book book = new Book(10L, "name", 2000, user, new HashSet<>(), new HashSet<>());

        user.setId(1L);

        when(bookRepository.findById(10L)).thenReturn(Optional.of(book));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        BookDtoResponse bookDtoResponse = bookService.returnBook(10L, user);

        Assertions.assertAll(
                () -> verify(bookRepository).save(any()),
                () -> Assertions.assertNull(bookDtoResponse.getUser())
        );
    }

    @Test
    public void testReturnBookFail() {
        BookService bookService = new BookServiceImpl(bookRepository, genreRepository, authorRepository);

        User owner = new User("username", "firstname", "lastname", "patronymic", 2000);

        User user = new User("username", "firstname", "lastname", "patronymic", 2000);

        Book book = new Book(10L, "name", 2000, owner, new HashSet<>(), new HashSet<>());

        user.setId(1L);

        owner.setId(2L);

        when(bookRepository.findById(10L)).thenReturn(Optional.of(book));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Assertions.assertThrows(
                ServerException.class, () -> bookService.returnBook(10L, user)
        );
    }

    @Test
    public void testAddAuthor() throws ServerException {
        BookService bookService = new BookServiceImpl(bookRepository, genreRepository, authorRepository);

        Author author = new Author(1L, "Иван", "Иванов", "Иванович", 1980, "Биография", new ArrayList<>());

        Book book = new Book(10L, "name", 2000, null, new HashSet<>(), new HashSet<>());

        when(bookRepository.findById(10L)).thenReturn(Optional.of(book));

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        IdDto idDto = new IdDto(1L);

        BookDtoResponse response = bookService.addAuthor(idDto, 10L);

        Assertions.assertAll(
                () -> verify(bookRepository).save(any()),
                () -> Assertions.assertEquals(1, response.getAuthors().size())
        );
    }

    @Test
    public void testDeleteAuthor() throws ServerException {
        BookService bookService = new BookServiceImpl(bookRepository, genreRepository, authorRepository);

        Author author = new Author(1L, "Иван", "Иванов", "Иванович", 1980, "Биография", new ArrayList<>());

        Book book = new Book(10L, "name", 2000, null, new HashSet<>(), new HashSet<>());

        book.getAuthors().add(author);

        when(bookRepository.findById(10L)).thenReturn(Optional.of(book));

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        BookDtoResponse response = bookService.deleteAuthor(1L, 10L);

        Assertions.assertAll(
                () -> verify(bookRepository).save(any()),
                () -> Assertions.assertEquals(0, response.getAuthors().size())
        );
    }
}
