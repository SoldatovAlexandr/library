package edu.asoldatov.library.endpoint;

import edu.asoldatov.library.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
public class AuthorEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorEndpoint.class);

    private final AuthorService authorService;

    @Autowired
    public AuthorEndpoint(AuthorService authorService) {
        this.authorService = authorService;
    }

    //добавление/редактирование автора;
    //назначение/удаление книги автору;
    //добавить читателя.

}
