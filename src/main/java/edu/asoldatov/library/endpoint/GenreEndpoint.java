package edu.asoldatov.library.endpoint;

import edu.asoldatov.library.dto.request.GenreDtoRequest;
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
    public GenreDtoResponse createGenre(@Valid @RequestBody GenreDtoRequest genreDtoRequest) {
        LOGGER.info("GenreEndpoint create genre");
        return genreService.createGenre(genreDtoRequest);
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
                                        @Valid @RequestBody GenreDtoRequest genreDtoRequest) throws ServerException {
        LOGGER.info("GenreEndpoint update genre");
        return genreService.updateGenre(genreDtoRequest, genreId);
    }

    @DeleteMapping(value = "/{genreId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteGenre(@PathVariable("genreId") long genreId) throws ServerException {
        LOGGER.info("GenreEndpoint delete genre");
        genreService.deleteGenre(genreId);
    }
}
