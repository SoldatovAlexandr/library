package edu.asoldatov.library.endpoint;

import edu.asoldatov.library.dto.request.CreateBookDtoRequest;
import edu.asoldatov.library.dto.request.UpdateBookDtoRequest;
import edu.asoldatov.library.dto.response.BookDtoResponse;
import edu.asoldatov.library.dto.response.EmptyDtoResponse;
import edu.asoldatov.library.dto.response.UserDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/books")
public class BookEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookEndpoint.class);

    private final BookService bookService;

    @Autowired
    public BookEndpoint(BookService bookService) {
        this.bookService = bookService;
    }

    //редактор
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookDtoResponse createBook(@Valid @RequestBody CreateBookDtoRequest request) throws ServerException {
        LOGGER.info("BookEndpoint create book");
        return bookService.createBook(request);
    }

    //редактор
    @PutMapping(value = "/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookDtoResponse updateBook(@Valid @RequestBody UpdateBookDtoRequest request,
                                      @PathVariable("bookId") long bookId) throws ServerException {
        LOGGER.info("BookEndpoint update book");
        return bookService.updateBook(request, bookId);
    }

    //редактор
    //просмотр у кого находится книга;
    @GetMapping(value = "/{bookId}/owners", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDtoResponse getBookOwner(@PathVariable("bookId") long bookId) {
        LOGGER.info("BookEndpoint get book owner");
        return bookService.getBookOwner(bookId);
    }

    //читатель
    //взять книгу на время
    @PostMapping(value = "/{bookId}/owners", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmptyDtoResponse takeBook(@PathVariable("bookId") long bookId) {
        LOGGER.info("BookEndpoint take the book");
        return bookService.takeBook(bookId);
    }


    //читатель
    //вернуть книгу;
    @DeleteMapping(value = "/{bookId}/owners", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmptyDtoResponse returnBook(@PathVariable("bookId") long bookId) {
        LOGGER.info("BookEndpoint return book");
        return bookService.returnBook(bookId);
    }
}
