package edu.asoldatov.library.service;

import edu.asoldatov.library.dao.BookDao;
import edu.asoldatov.library.dao.GenreDao;
import edu.asoldatov.library.dto.mapper.BookDtoMapper;
import edu.asoldatov.library.dto.request.CreateBookDtoRequest;
import edu.asoldatov.library.dto.request.UpdateBookDtoRequest;
import edu.asoldatov.library.dto.response.BookDtoResponse;
import edu.asoldatov.library.dto.response.EmptyDtoResponse;
import edu.asoldatov.library.dto.response.UserDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.model.Book;
import edu.asoldatov.library.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceBase implements BookService {

    @Autowired
    public BookServiceImpl(BookDao bookDao, GenreDao genreDao) {
        super(bookDao, genreDao);
    }

    @Override
    public BookDtoResponse createBook(CreateBookDtoRequest request) throws ServerException {
        Book book = BookDtoMapper.INSTANCE.toBook(request);

        long genreId = request.getGenreId();

        Genre genre = getGenreById(genreId);

        book.setGenre(genre);

        bookDao.insert(book);

        return BookDtoMapper.INSTANCE.toBookDtoResponse(book);
    }

    @Override
    public BookDtoResponse updateBook(UpdateBookDtoRequest request, long bookId) throws ServerException {
        Book book = getBookById(bookId);

        String name = request.getName();

        if (name != null) {
            book.setName(name);
        }

        Integer yearOfPublishing = request.getYearOfPublishing();

        if (yearOfPublishing != null) {
            book.setYearOfPublishing(yearOfPublishing);
        }

        Long genreId = request.getGenreId();

        if (genreId != null) {
            Genre genre = getGenreById(genreId);

            book.setGenre(genre);
        }

        bookDao.update(book);

        return BookDtoMapper.INSTANCE.toBookDtoResponse(book);
    }


    @Override
    public UserDtoResponse getBookOwner(long bookId) {
        return null;
    }

    @Override
    public EmptyDtoResponse takeBook(long bookId) {
        return null;
    }

    @Override
    public EmptyDtoResponse returnBook(long bookId) {
        return null;
    }
}
