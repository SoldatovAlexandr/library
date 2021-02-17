package edu.asoldatov.library.service.impl;

import edu.asoldatov.library.dto.mapper.GenreDtoMapper;
import edu.asoldatov.library.dto.request.GenreDtoRequest;
import edu.asoldatov.library.dto.response.GenreDtoResponse;
import edu.asoldatov.library.exception.ServerException;
import edu.asoldatov.library.model.Genre;
import edu.asoldatov.library.repository.GenreRepository;
import edu.asoldatov.library.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final static GenreDtoMapper GENRE_DTO_MAPPER = GenreDtoMapper.INSTANCE;
    private final GenreRepository genreRepository;


    @Override
    public GenreDtoResponse createGenre(GenreDtoRequest genreDtoRequest) {
        Genre genre = GENRE_DTO_MAPPER.toGenre(genreDtoRequest);

        genreRepository.save(genre);

        return GENRE_DTO_MAPPER.toGenreDtoResponse(genre);
    }

    @Override
    public GenreDtoResponse getGenre(long genreId) throws ServerException {
        Genre genre = getGenreById(genreId);

        return GENRE_DTO_MAPPER.toGenreDtoResponse(genre);
    }

    @Override
    public List<GenreDtoResponse> getGenres() {
        List<Genre> genres = genreRepository.findAll();

        return GENRE_DTO_MAPPER.toGenreDtoResponses(genres);
    }

    @Override
    public GenreDtoResponse updateGenre(GenreDtoRequest genreDtoRequest, long genreId) throws ServerException {
        Genre genre = getGenreById(genreId);

        String name = genreDtoRequest.getName();

        genre.setName(name);

        genreRepository.save(genre);

        return GENRE_DTO_MAPPER.toGenreDtoResponse(genre);
    }

    @Override
    public void deleteGenre(long genreId) throws ServerException {
        Genre genre = getGenreById(genreId);

        genreRepository.delete(genre);
    }

    private Genre getGenreById(long genreId) throws ServerException {
        return genreRepository.findById(genreId).orElseThrow(() -> new ServerException("wrong.genre.id"));
    }
}
