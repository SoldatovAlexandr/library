package edu.asoldatov.library.service;

import edu.asoldatov.library.dto.request.CreateGenreDtoRequest;
import edu.asoldatov.library.dto.response.EmptyDtoResponse;
import edu.asoldatov.library.dto.response.GenreDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;

import java.util.List;

public interface GenreService {

    GenreDtoResponse createGenre(CreateGenreDtoRequest request);

    GenreDtoResponse getGenre(long genreId) throws ServerException;

    List<GenreDtoResponse> getGenres();

    GenreDtoResponse updateGenre(CreateGenreDtoRequest request, long genreId) throws ServerException;

    EmptyDtoResponse deleteGenre(long genreId) throws ServerException;
}
