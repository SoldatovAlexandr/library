package edu.asoldatov.library.service.impl;

import edu.asoldatov.library.dto.mapper.GenreDtoMapper;
import edu.asoldatov.library.dto.request.GenreDtoRequest;
import edu.asoldatov.library.dto.response.EmptyDtoResponse;
import edu.asoldatov.library.dto.response.GenreDtoResponse;
import edu.asoldatov.library.erroritem.code.ServerErrorCodeWithField;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.model.Genre;
import edu.asoldatov.library.repository.GenreRepository;
import edu.asoldatov.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final static GenreDtoMapper GENRE_DTO_MAPPER = GenreDtoMapper.INSTANCE;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


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
        Iterable<Genre> genres = genreRepository.findAll();

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
    public EmptyDtoResponse deleteGenre(long genreId) throws ServerException {
        Genre genre = getGenreById(genreId);

        genreRepository.delete(genre);

        return new EmptyDtoResponse();
    }

    private Genre getGenreById(long genreId) throws ServerException {
        return genreRepository.findById(genreId).orElseThrow(() -> new ServerException(ServerErrorCodeWithField.WRONG_GENRE_ID));
    }
}
