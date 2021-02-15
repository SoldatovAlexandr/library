package edu.asoldatov.library.service;


import edu.asoldatov.library.dto.request.AddAuthorToBookDtoRequest;
import edu.asoldatov.library.dto.request.BookDtoRequest;
import edu.asoldatov.library.dto.request.DeleteAuthorFromBookDtoRequest;
import edu.asoldatov.library.dto.response.BookDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.model.User;

import java.util.List;

public interface BookService {
    BookDtoResponse createBook(BookDtoRequest bookDtoRequest) throws ServerException;

    BookDtoResponse updateBook(BookDtoRequest bookDtoRequest, long bookId) throws ServerException;

    BookDtoResponse takeBook(long bookId, User user) throws ServerException;

    BookDtoResponse returnBook(long bookId, User user) throws ServerException;

    List<BookDtoResponse> getAllBooks();

    BookDtoResponse getBook(long bookId) throws ServerException;

    BookDtoResponse addAuthor(AddAuthorToBookDtoRequest request, long bookId) throws ServerException;

    BookDtoResponse deleteAuthor(DeleteAuthorFromBookDtoRequest request, long bookId) throws ServerException;
}
