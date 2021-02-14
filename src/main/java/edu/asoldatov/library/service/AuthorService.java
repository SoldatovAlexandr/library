package edu.asoldatov.library.service;

import edu.asoldatov.library.dto.request.AddBookToAuthorDtoRequest;
import edu.asoldatov.library.dto.request.CreateAuthorDtoRequest;
import edu.asoldatov.library.dto.request.DeleteBookFromAuthorDtoRequest;
import edu.asoldatov.library.dto.request.UpdateAuthorDtoRequest;
import edu.asoldatov.library.dto.response.AuthorDtoResponse;
import edu.asoldatov.library.dto.response.EmptyDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;

import java.util.List;

public interface AuthorService {
    AuthorDtoResponse createAuthor(CreateAuthorDtoRequest request);

    AuthorDtoResponse updateAuthor(UpdateAuthorDtoRequest request, long authorId) throws ServerException;

    EmptyDtoResponse addBookToAuthor(long authorId, AddBookToAuthorDtoRequest request) throws ServerException;

    EmptyDtoResponse deleteBookFromAuthor(long authorId, DeleteBookFromAuthorDtoRequest request) throws ServerException;

    List<AuthorDtoResponse> getAllAuthors();

    AuthorDtoResponse getAuthor(long authorId) throws ServerException;
}
