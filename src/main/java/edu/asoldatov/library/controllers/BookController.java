package edu.asoldatov.library.controllers;

import edu.asoldatov.library.dto.IdDto;
import edu.asoldatov.library.dto.request.BookDtoRequest;
import edu.asoldatov.library.dto.response.BookDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.model.User;
import edu.asoldatov.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "/books")
    public String getBooksPage(Model model) {
        LOGGER.info("BookController get books page");

        List<BookDtoResponse> books = bookService.getAllBooks();

        model.addAttribute("books", books);

        model.addAttribute("book", new BookDtoRequest());

        return "books";
    }

    @PostMapping(path = "/books")
    public String addBook(@ModelAttribute(name = "book") @Valid BookDtoRequest bookDtoRequest,
                          Model model) throws ServerException {
        LOGGER.info("BookController add book");

        bookService.createBook(bookDtoRequest);

        List<BookDtoResponse> books = bookService.getAllBooks();

        model.addAttribute("books", books);

        return "books";
    }

    @GetMapping(path = "/books/{bookId}")
    public String getBookPage(Model model, @PathVariable("bookId") long bookId) throws ServerException {
        LOGGER.info("BookController get book page");

        BookDtoResponse book = bookService.getBook(bookId);

        model.addAttribute("book", book);

        model.addAttribute("addAuthor", new IdDto());

        model.addAttribute("updateBook", new BookDtoRequest());

        return "book";
    }

    @PostMapping(path = "/books/{bookId}")
    public String updateBook(Model model,
                             @PathVariable("bookId") long bookId,
                             BookDtoRequest bookDtoRequest) throws ServerException {
        LOGGER.info("BookController update book");

        BookDtoResponse book = bookService.updateBook(bookDtoRequest, bookId);

        model.addAttribute("book", book);

        model.addAttribute("addAuthor", new IdDto());

        model.addAttribute("updateBook", new BookDtoRequest());

        return "book";
    }

    @PostMapping(path = "/books/{bookId}/authors")
    public String addAuthorToBook(Model model,
                                  @PathVariable("bookId") long bookId,
                                  IdDto idDto
    ) throws ServerException {
        LOGGER.info("BookController add author to the book");

        BookDtoResponse book = bookService.addAuthor(idDto, bookId);

        model.addAttribute("book", book);

        model.addAttribute("addAuthor", new IdDto());

        model.addAttribute("updateBook", new BookDtoRequest());

        return "book";
    }

    @PostMapping(path = "/books/{bookId}/authors/{authorId}/delete")
    public String deleteAuthorFromBook(Model model,
                                       @PathVariable("bookId") long bookId,
                                       @PathVariable("authorId") long authorId
    ) throws ServerException {
        LOGGER.info("BookController delete author from the book");

        BookDtoResponse book = bookService.deleteAuthor(authorId, bookId);

        model.addAttribute("book", book);

        model.addAttribute("addAuthor", new IdDto());

        model.addAttribute("updateBook", new BookDtoRequest());

        return "book";
    }

    @PostMapping(value = "/books/{bookId}/take")
    public String takeBook(@PathVariable("bookId") long bookId,
                           Model model,
                           @AuthenticationPrincipal User user) throws ServerException {
        LOGGER.info("BookController take the book");

        BookDtoResponse book = bookService.takeBook(bookId, user);

        model.addAttribute("book", book);

        model.addAttribute("addAuthor", new IdDto());

        model.addAttribute("updateBook", new BookDtoRequest());

        return "book";
    }


    @PostMapping(value = "/books/{bookId}/return")
    public String returnBook(@PathVariable("bookId") long bookId,
                             Model model,
                             @AuthenticationPrincipal User user) throws ServerException {
        LOGGER.info("BookController return book");

        BookDtoResponse book = bookService.returnBook(bookId, user);

        model.addAttribute("book", book);

        model.addAttribute("addAuthor", new IdDto());

        model.addAttribute("updateBook", new BookDtoRequest());

        return "book";
    }
}
