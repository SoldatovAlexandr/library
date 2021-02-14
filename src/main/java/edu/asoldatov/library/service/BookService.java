package edu.asoldatov.library.service;


import edu.asoldatov.library.dto.request.AddAuthorToBookDtoRequest;
import edu.asoldatov.library.dto.request.CreateBookDtoRequest;
import edu.asoldatov.library.dto.request.DeleteAuthorFromBookDtoRequest;
import edu.asoldatov.library.dto.request.UpdateBookDtoRequest;
import edu.asoldatov.library.dto.response.BookDtoResponse;
import edu.asoldatov.library.dto.response.UserDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.model.User;

import java.util.List;

public interface BookService {
    BookDtoResponse createBook(CreateBookDtoRequest request) throws ServerException;

    BookDtoResponse updateBook(UpdateBookDtoRequest request, long bookId) throws ServerException;

    UserDtoResponse getBookOwner(long bookId);

    BookDtoResponse takeBook(long bookId, User user) throws ServerException;

    BookDtoResponse returnBook(long bookId, User user) throws ServerException;

    List<BookDtoResponse> getAllBooks();

    BookDtoResponse getBook(long bookId) throws ServerException;

    BookDtoResponse addAuthor(AddAuthorToBookDtoRequest request, long bookId) throws ServerException;

    BookDtoResponse deleteAuthor(DeleteAuthorFromBookDtoRequest request, long bookId) throws ServerException;
}
