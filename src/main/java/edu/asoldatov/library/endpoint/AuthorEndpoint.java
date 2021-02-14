package edu.asoldatov.library.endpoint;

import edu.asoldatov.library.dto.request.AddBookToAuthorDtoRequest;
import edu.asoldatov.library.dto.request.CreateAuthorDtoRequest;
import edu.asoldatov.library.dto.request.DeleteBookFromAuthorDtoRequest;
import edu.asoldatov.library.dto.request.UpdateAuthorDtoRequest;
import edu.asoldatov.library.dto.response.AuthorDtoResponse;
import edu.asoldatov.library.dto.response.EmptyDtoResponse;
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
    public AuthorDtoResponse createAuthor(@Valid @RequestBody CreateAuthorDtoRequest request) {
        LOGGER.info("AuthorEndpoint create author");
        return authorService.createAuthor(request);
    }

    @PutMapping(value = "/{authorId}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public AuthorDtoResponse updateAuthor(@Valid @RequestBody UpdateAuthorDtoRequest request,
                                          @PathVariable("authorId") long authorId) throws ServerException {
        LOGGER.info("AuthorEndpoint create author");
        return authorService.updateAuthor(request, authorId);
    }

    //назначение/удаление книги автору;

    @PostMapping(value = "/{authorId}/books", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmptyDtoResponse addBookToAuthor(@PathVariable("authorId") long authorId,
                                            @Valid @RequestBody AddBookToAuthorDtoRequest request)
            throws ServerException {
        LOGGER.info("AuthorEndpoint add book to the author");
        return authorService.addBookToAuthor(authorId, request);
    }

    @DeleteMapping(value = "/{authorId}/books", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmptyDtoResponse deleteBookFromAuthor(@PathVariable("authorId") long authorId,
                                                 @Valid @RequestBody DeleteBookFromAuthorDtoRequest request)
            throws ServerException {
        LOGGER.info("AuthorEndpoint delete book from the author");
        return authorService.deleteBookFromAuthor(authorId, request);
    }
}
