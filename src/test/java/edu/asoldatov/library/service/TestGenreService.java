package edu.asoldatov.library.service;

import edu.asoldatov.library.dto.request.GenreDtoRequest;
import edu.asoldatov.library.dto.response.GenreDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.model.Genre;
import edu.asoldatov.library.repository.GenreRepository;
import edu.asoldatov.library.service.impl.GenreServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TestGenreService {

    private final static String GENRE_NAME = "GenreName";
    private final static String NEW_GENRE_NAME = "NewGenreName";
    @MockBean
    private GenreRepository genreRepository;

    @Test
    public void testCreateGenre() {
        GenreService genreService = new GenreServiceImpl(genreRepository);

        GenreDtoRequest genreDtoRequest = new GenreDtoRequest(GENRE_NAME);

        Genre genre = new Genre(GENRE_NAME);

        GenreDtoResponse expectedResponse = new GenreDtoResponse(0, GENRE_NAME);

        GenreDtoResponse genreDtoResponse = genreService.createGenre(genreDtoRequest);

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedResponse, genreDtoResponse),
                () -> verify(genreRepository).save(genre)
        );
    }

    @Test
    public void testGetGenre() throws ServerException {
        GenreService genreService = new GenreServiceImpl(genreRepository);

        Genre genre = new Genre(1L, GENRE_NAME);

        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));

        GenreDtoResponse expectedResponse = new GenreDtoResponse(1L, GENRE_NAME);

        GenreDtoResponse genreDtoResponse = genreService.getGenre(1L);

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedResponse, genreDtoResponse),
                () -> verify(genreRepository).findById(1L)
        );
    }

    @Test
    public void testGetGenres() {
        GenreService genreService = new GenreServiceImpl(genreRepository);

        List<Genre> genres = new ArrayList<>();

        genres.add(new Genre(1L, GENRE_NAME));

        genres.add(new Genre(2L, GENRE_NAME));

        when(genreRepository.findAll()).thenReturn(genres);

        List<GenreDtoResponse> responses = genreService.getGenres();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, responses.size()),
                () -> verify(genreRepository).findAll()
        );
    }

    @Test
    public void testUpdateGenre() throws ServerException {
        GenreService genreService = new GenreServiceImpl(genreRepository);

        Genre genre = new Genre(1L, GENRE_NAME);

        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));

        GenreDtoRequest genreDtoRequest = new GenreDtoRequest(NEW_GENRE_NAME);

        GenreDtoResponse expectedResponse = new GenreDtoResponse(1L, NEW_GENRE_NAME);

        GenreDtoResponse genreDtoResponse = genreService.updateGenre(genreDtoRequest, 1L);

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedResponse, genreDtoResponse),
                () -> verify(genreRepository).save(any())
        );
    }

    @Test
    public void testDeleteGenre() throws ServerException {
        GenreService genreService = new GenreServiceImpl(genreRepository);

        Genre genre = new Genre(1L, GENRE_NAME);

        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));

        genreService.deleteGenre(1L);

        Assertions.assertAll(
                () -> verify(genreRepository).delete(genre),
                () -> verify(genreRepository).findById(1L)
        );
    }
}
