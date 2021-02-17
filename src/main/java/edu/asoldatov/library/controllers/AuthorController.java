package edu.asoldatov.library.controllers;

import edu.asoldatov.library.dto.request.AuthorDtoRequest;
import edu.asoldatov.library.dto.response.AuthorDtoResponse;
import edu.asoldatov.library.exception.ServerException;
import edu.asoldatov.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class AuthorController {

    private final AuthorService authorService;

    @RequestMapping(path = "/authors", method = RequestMethod.GET)
    public String getAuthorsPage(Model model) {
        log.info("AuthorController get authors page");

        List<AuthorDtoResponse> authors = authorService.getAllAuthors();

        model.addAttribute("authors", authors);

        model.addAttribute("author", new AuthorDtoRequest());

        return "authors";
    }

    @RequestMapping(path = "/authors", method = RequestMethod.POST)
    public String addAuthor(@ModelAttribute(name = "author") @Valid AuthorDtoRequest authorDtoRequest,
                            BindingResult bindingResult,
                            Model model) {
        log.info("AuthorController add new author");

        if (!bindingResult.hasErrors()) {
            authorService.createAuthor(authorDtoRequest);
        }

        List<AuthorDtoResponse> authors = authorService.getAllAuthors();

        model.addAttribute("authors", authors);

        return "authors";
    }

    @RequestMapping(path = "/authors/{authorId}", method = RequestMethod.GET)
    public String getAuthorPage(Model model, @PathVariable("authorId") long authorId) throws ServerException {
        log.info("AuthorController get author page");

        AuthorDtoResponse author = authorService.getAuthor(authorId);

        model.addAttribute("author", author);

        model.addAttribute("updateAuthor", new AuthorDtoRequest());

        return "author";
    }

    @RequestMapping(path = "/authors/{authorId}", method = RequestMethod.POST)
    public String updateAuthor(Model model,
                               @ModelAttribute(name = "updateAuthor") @Valid AuthorDtoRequest authorDtoRequest,
                               BindingResult bindingResult,
                               @PathVariable("authorId") long authorId

    ) throws ServerException {
        log.info("AuthorController update author");

        AuthorDtoResponse author;

        if (!bindingResult.hasErrors()) {
            author = authorService.updateAuthor(authorDtoRequest, authorId);

            model.addAttribute("updateAuthor", new AuthorDtoRequest());
        } else {
            author = authorService.getAuthor(authorId);
        }

        model.addAttribute("author", author);

        return "author";
    }
}
