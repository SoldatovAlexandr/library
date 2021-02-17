package edu.asoldatov.library.service;

import edu.asoldatov.library.dto.request.GenreDtoRequest;
import edu.asoldatov.library.dto.response.GenreDtoResponse;
import edu.asoldatov.library.exception.ServerException;

import java.util.List;

public interface GenreService {

    GenreDtoResponse createGenre(GenreDtoRequest genreDtoRequest);

    GenreDtoResponse getGenre(long genreId) throws ServerException;

    List<GenreDtoResponse> getGenres();

    GenreDtoResponse updateGenre(GenreDtoRequest genreDtoRequest, long genreId) throws ServerException;

    void deleteGenre(long genreId) throws ServerException;
}
