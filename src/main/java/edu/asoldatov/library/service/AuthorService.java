package edu.asoldatov.library.service;

import edu.asoldatov.library.dto.request.AuthorDtoRequest;
import edu.asoldatov.library.dto.response.AuthorDtoResponse;
import edu.asoldatov.library.exception.ServerException;

import java.util.List;

public interface AuthorService {
    AuthorDtoResponse createAuthor(AuthorDtoRequest authorDtoRequest);

    AuthorDtoResponse updateAuthor(AuthorDtoRequest authorDtoRequest, long authorId) throws ServerException;

    List<AuthorDtoResponse> getAllAuthors();

    AuthorDtoResponse getAuthor(long authorId) throws ServerException;
}
