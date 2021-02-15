package edu.asoldatov.library.endpoint;

import edu.asoldatov.library.dto.request.CreateGenreDtoRequest;
import edu.asoldatov.library.dto.response.EmptyDtoResponse;
import edu.asoldatov.library.dto.response.GenreDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//только редактор
@RestController
@RequestMapping("/api/genres")
public class GenreEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookEndpoint.class);

    private final GenreService genreService;

    @Autowired
    public GenreEndpoint(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenreDtoResponse createGenre(@Valid @RequestBody CreateGenreDtoRequest request) {
        LOGGER.info("GenreEndpoint create genre");
        return genreService.createGenre(request);
    }

    @GetMapping(value = "/{genreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GenreDtoResponse getGenre(@PathVariable("genreId") long genreId) throws ServerException {
        LOGGER.info("GenreEndpoint get genre");
        return genreService.getGenre(genreId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GenreDtoResponse> getGenres() {
        LOGGER.info("GenreEndpoint get genres");
        return genreService.getGenres();
    }

    @PutMapping(value = "/{genreId}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenreDtoResponse updateGenre(@PathVariable("genreId") long genreId,
                                        @Valid @RequestBody CreateGenreDtoRequest request) throws ServerException {
        LOGGER.info("GenreEndpoint update genre");
        return genreService.updateGenre(request, genreId);
    }

    @DeleteMapping(value = "/{genreId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmptyDtoResponse deleteGenre(@PathVariable("genreId") long genreId) throws ServerException {
        LOGGER.info("GenreEndpoint delete genre");
        return genreService.deleteGenre(genreId);
    }
}
