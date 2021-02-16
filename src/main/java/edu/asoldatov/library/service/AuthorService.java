package edu.asoldatov.library.service;

import edu.asoldatov.library.dto.IdDto;
import edu.asoldatov.library.dto.request.AuthorDtoRequest;
import edu.asoldatov.library.dto.response.AuthorDtoResponse;
import edu.asoldatov.library.dto.response.EmptyDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;

import java.util.List;

public interface AuthorService {
    AuthorDtoResponse createAuthor(AuthorDtoRequest authorDtoRequest);

    AuthorDtoResponse updateAuthor(AuthorDtoRequest authorDtoRequest, long authorId) throws ServerException;

    EmptyDtoResponse addBookToAuthor(long authorId, IdDto idDto) throws ServerException;

    EmptyDtoResponse deleteBookFromAuthor(long authorId, IdDto idDto) throws ServerException;

    List<AuthorDtoResponse> getAllAuthors();

    AuthorDtoResponse getAuthor(long authorId) throws ServerException;
}
