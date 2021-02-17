package edu.asoldatov.library.service;

import edu.asoldatov.library.dto.request.AuthorDtoRequest;
import edu.asoldatov.library.dto.response.AuthorDtoResponse;
import edu.asoldatov.library.exception.ServerException;
import edu.asoldatov.library.model.Author;
import edu.asoldatov.library.repository.AuthorRepository;
import edu.asoldatov.library.repository.BookRepository;
import edu.asoldatov.library.service.impl.AuthorServiceImpl;
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
public class TestAuthorService {

    @MockBean
    private AuthorRepository authorRepository;


    @Test
    public void testCreateAuthor() {
        AuthorService authorService = new AuthorServiceImpl(authorRepository);

        AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest("firstname", "lastname", "patronymic", 2000, "biography");

        AuthorDtoResponse expectedResponse = new AuthorDtoResponse(null, "firstname", "lastname", "patronymic", 2000, "biography", new HashSet<>());

        AuthorDtoResponse response = authorService.createAuthor(authorDtoRequest);

        Assertions.assertAll(
                () -> verify(authorRepository).save(any()),
                () -> Assertions.assertEquals(expectedResponse, response)
        );
    }

    @Test
    public void testUpdateAuthor() throws ServerException {
        AuthorService authorService = new AuthorServiceImpl(authorRepository);

        Author author = new Author(1L, "Иван", "Иванов", "Иванович", 1980, "Биография", new ArrayList<>());

        AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest("firstname", "lastname", "patronymic", 2000, "biography");

        AuthorDtoResponse expectedResponse = new AuthorDtoResponse(1L, "firstname", "lastname", "patronymic", 2000, "biography", new HashSet<>());

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        AuthorDtoResponse response = authorService.updateAuthor(authorDtoRequest, 1L);

        Assertions.assertAll(
                () -> verify(authorRepository).save(any()),
                () -> Assertions.assertEquals(expectedResponse, response)
        );
    }

    @Test
    public void testGetAuthor() throws ServerException {
        AuthorService authorService = new AuthorServiceImpl(authorRepository);

        Author author = new Author(1L, "Иван", "Иванов", "Иванович", 1980, "Биография", new ArrayList<>());

        AuthorDtoResponse expectedResponse = new AuthorDtoResponse(1L, "Иван", "Иванов", "Иванович", 1980, "Биография", new HashSet<>());

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        AuthorDtoResponse response = authorService.getAuthor(1L);

        Assertions.assertAll(
                () -> verify(authorRepository).findById(1L),
                () -> Assertions.assertEquals(expectedResponse, response)
        );
    }

    @Test
    public void testGetAllAuthors() {
        AuthorService authorService = new AuthorServiceImpl(authorRepository);

        List<Author> authors = new ArrayList<>();

        authors.add(new Author(1L, "Иван", "Иванов", "Иванович", 1980, "Биография", new ArrayList<>()));

        authors.add(new Author(2L, "Иван", "Иванов", "Иванович", 1980, "Биография", new ArrayList<>()));

        when(authorRepository.findAll()).thenReturn(authors);

        List<AuthorDtoResponse> responses = authorService.getAllAuthors();

        Assertions.assertAll(
                () -> verify(authorRepository).findAll(),
                () -> Assertions.assertEquals(2, responses.size())
        );
    }
}
