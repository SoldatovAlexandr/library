package edu.asoldatov.library.service;


import edu.asoldatov.library.dto.request.CreateBookDtoRequest;
import edu.asoldatov.library.dto.request.UpdateBookDtoRequest;
import edu.asoldatov.library.dto.response.BookDtoResponse;
import edu.asoldatov.library.dto.response.EmptyDtoResponse;
import edu.asoldatov.library.dto.response.UserDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;

public interface BookService {
    BookDtoResponse createBook(CreateBookDtoRequest request) throws ServerException;

    BookDtoResponse updateBook(UpdateBookDtoRequest request, long bookId) throws ServerException;

    UserDtoResponse getBookOwner(long bookId);

    EmptyDtoResponse takeBook(long bookId);

    EmptyDtoResponse returnBook(long bookId);
}
