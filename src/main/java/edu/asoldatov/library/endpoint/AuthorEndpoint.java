package edu.asoldatov.library.endpoint;

import edu.asoldatov.library.dto.IdDto;
import edu.asoldatov.library.dto.request.AuthorDtoRequest;
import edu.asoldatov.library.dto.response.AuthorDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public AuthorDtoResponse createAuthor(@Valid @RequestBody AuthorDtoRequest authorDtoRequest) {
        LOGGER.info("AuthorEndpoint create author");
        return authorService.createAuthor(authorDtoRequest);
    }

    @PutMapping(value = "/{authorId}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public AuthorDtoResponse updateAuthor(@RequestBody AuthorDtoRequest authorDtoRequest,
                                          @PathVariable("authorId") long authorId) throws ServerException {
        LOGGER.info("AuthorEndpoint create author");
        return authorService.updateAuthor(authorDtoRequest, authorId);
    }

    //назначение/удаление книги автору;

    @PostMapping(value = "/{authorId}/books", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBookToAuthor(@PathVariable("authorId") long authorId,
                                @Valid @RequestBody IdDto idDto)
            throws ServerException {
        LOGGER.info("AuthorEndpoint add book to the author");
        authorService.addBookToAuthor(authorId, idDto);
    }

    @DeleteMapping(value = "/{authorId}/books", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteBookFromAuthor(@PathVariable("authorId") long authorId,
                                     @Valid @RequestBody IdDto idDto)
            throws ServerException {
        LOGGER.info("AuthorEndpoint delete book from the author");
        authorService.deleteBookFromAuthor(authorId, idDto);
    }
}
