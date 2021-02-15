package edu.asoldatov.library.controllers;

import edu.asoldatov.library.dto.request.AuthorDtoRequest;
import edu.asoldatov.library.dto.response.AuthorDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(path = "/authors")
    public String getAuthorsPage(Model model) {
        LOGGER.info("AuthorController get authors page");

        List<AuthorDtoResponse> authors = authorService.getAllAuthors();

        model.addAttribute("authors", authors);

        model.addAttribute("author", new AuthorDtoRequest());

        return "authors";
    }

    @PostMapping(path = "/authors")
    public String addAuthor(@ModelAttribute(name = "author") @Valid AuthorDtoRequest authorDtoRequest, Model model) {
        LOGGER.info("AuthorController add new author");

        authorService.createAuthor(authorDtoRequest);

        List<AuthorDtoResponse> authors = authorService.getAllAuthors();

        model.addAttribute("authors", authors);

        return "authors";
    }

    @GetMapping(path = "/authors/{authorId}")
    public String getAuthorPage(Model model, @PathVariable("authorId") long authorId) throws ServerException {
        LOGGER.info("AuthorController get author page");

        AuthorDtoResponse author = authorService.getAuthor(authorId);

        model.addAttribute("author", author);

        model.addAttribute("updateAuthor", new AuthorDtoRequest());

        return "author";
    }

    @PostMapping(path = "/authors/{authorId}")
    public String updateAuthor(Model model,
                               @PathVariable("authorId") long authorId,
                               AuthorDtoRequest request) throws ServerException {
        LOGGER.info("AuthorController update author");

        AuthorDtoResponse author = authorService.updateAuthor(request, authorId);

        model.addAttribute("author", author);

        model.addAttribute("updateAuthor", new AuthorDtoRequest());

        return "author";
    }
}
