package edu.asoldatov.library.service;


import edu.asoldatov.library.dto.IdDto;
import edu.asoldatov.library.dto.request.BookDtoRequest;
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

    BookDtoResponse addAuthor(IdDto idDto, long bookId) throws ServerException;

    BookDtoResponse deleteAuthor(long authorId, long bookId) throws ServerException;
}
