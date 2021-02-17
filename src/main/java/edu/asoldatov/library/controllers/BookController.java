package edu.asoldatov.library.controllers;

import edu.asoldatov.library.dto.IdDto;
import edu.asoldatov.library.dto.request.BookDtoRequest;
import edu.asoldatov.library.dto.response.BookDtoResponse;
import edu.asoldatov.library.exception.ServerException;
import edu.asoldatov.library.model.User;
import edu.asoldatov.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @RequestMapping(path = "/books", method = RequestMethod.GET)
    public String getBooksPage(Model model) {
        log.info("BookController get books page");

        List<BookDtoResponse> books = bookService.getAllBooks();

        model.addAttribute("books", books);

        model.addAttribute("book", new BookDtoRequest());

        return "books";
    }

    @RequestMapping(path = "/books", method = RequestMethod.POST)
    public String addBook(@ModelAttribute(name = "book") @Valid BookDtoRequest bookDtoRequest,
                          BindingResult bindingResult,
                          Model model) throws ServerException {
        log.info("BookController add book");

        if (!bindingResult.hasErrors()) {
            bookService.createBook(bookDtoRequest);
        }

        List<BookDtoResponse> books = bookService.getAllBooks();

        model.addAttribute("books", books);

        return "books";
    }

    @RequestMapping(path = "/books/{bookId}", method = RequestMethod.GET)
    public String getBookPage(Model model, @PathVariable("bookId") long bookId) throws ServerException {
        log.info("BookController get book page");

        BookDtoResponse book = bookService.getBook(bookId);

        model.addAttribute("book", book);

        model.addAttribute("addAuthor", new IdDto());

        model.addAttribute("updateBook", new BookDtoRequest());

        return "book";
    }

    @RequestMapping(path = "/books/{bookId}", method = RequestMethod.POST)
    public String updateBook(Model model,
                             @PathVariable("bookId") long bookId,
                             @ModelAttribute(name = "updateBook") @Valid BookDtoRequest bookDtoRequest,
                             BindingResult bindingResult
    ) throws ServerException {
        log.info("BookController update book");

        BookDtoResponse book;

        if (!bindingResult.hasErrors()) {
            book = bookService.updateBook(bookDtoRequest, bookId);

            model.addAttribute("updateBook", new BookDtoRequest());
        } else {
            book = bookService.getBook(bookId);
        }

        model.addAttribute("book", book);

        model.addAttribute("addAuthor", new IdDto());

        return "book";
    }

    @RequestMapping(path = "/books/{bookId}/authors", method = RequestMethod.POST)
    public String addAuthorToBook(Model model,
                                  @PathVariable("bookId") long bookId,
                                  IdDto idDto
    ) throws ServerException {
        log.info("BookController add author to the book");

        BookDtoResponse book = bookService.addAuthor(idDto, bookId);

        model.addAttribute("book", book);

        model.addAttribute("addAuthor", new IdDto());

        model.addAttribute("updateBook", new BookDtoRequest());

        return "book";
    }

    @RequestMapping(path = "/books/{bookId}/authors/{authorId}/delete", method = RequestMethod.POST)
    public String deleteAuthorFromBook(Model model,
                                       @PathVariable("bookId") long bookId,
                                       @PathVariable("authorId") long authorId
    ) throws ServerException {
        log.info("BookController delete author from the book");

        BookDtoResponse book = bookService.deleteAuthor(authorId, bookId);

        model.addAttribute("book", book);

        model.addAttribute("addAuthor", new IdDto());

        model.addAttribute("updateBook", new BookDtoRequest());

        return "book";
    }

    @RequestMapping(path = "/books/{bookId}/take", method = RequestMethod.POST)
    public String takeBook(@PathVariable("bookId") long bookId,
                           Model model,
                           @AuthenticationPrincipal User user) throws ServerException {
        log.info("BookController take the book");

        BookDtoResponse book = bookService.takeBook(bookId, user);

        model.addAttribute("book", book);

        model.addAttribute("addAuthor", new IdDto());

        model.addAttribute("updateBook", new BookDtoRequest());

        return "book";
    }

    @RequestMapping(path = "/books/{bookId}/return", method = RequestMethod.POST)
    public String returnBook(@PathVariable("bookId") long bookId,
                             Model model,
                             @AuthenticationPrincipal User user) throws ServerException {
        log.info("BookController return book");

        BookDtoResponse book = bookService.returnBook(bookId, user);

        model.addAttribute("book", book);

        model.addAttribute("addAuthor", new IdDto());

        model.addAttribute("updateBook", new BookDtoRequest());

        return "book";
    }

    @RequestMapping(path = "/books/{bookId}/genres/{genreId}/delete", method = RequestMethod.POST)
    public String deleteGenreFromBook(
            Model model,
            @PathVariable("genreId") long genreId,
            @PathVariable("bookId") long bookId
    ) throws ServerException {
        log.info("GenreController delete genre from book");

        BookDtoResponse book = bookService.deleteGenre(bookId, genreId);

        model.addAttribute("book", book);

        model.addAttribute("addAuthor", new IdDto());

        model.addAttribute("updateBook", new BookDtoRequest());

        return "book";
    }
}
