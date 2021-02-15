package edu.asoldatov.library.controllers;

import edu.asoldatov.library.dto.request.GenreDtoRequest;
import edu.asoldatov.library.dto.response.GenreDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.service.GenreService;
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
public class GenreController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenreController.class);

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(path = "/genres")
    public String getGenresPage(Model model) {
        LOGGER.info("GenreController get genres page");

        List<GenreDtoResponse> genres = genreService.getGenres();

        model.addAttribute("genres", genres);

        model.addAttribute("genre", new GenreDtoRequest());

        return "genres";
    }

    @PostMapping(path = "/genres")
    public String addGenre(@ModelAttribute(name = "genre") @Valid GenreDtoRequest genreDtoRequest, Model model) {
        LOGGER.info("GenreController add new genre");

        genreService.createGenre(genreDtoRequest);

        List<GenreDtoResponse> genres = genreService.getGenres();

        model.addAttribute("genres", genres);

        return "genres";
    }

    @GetMapping(path = "/genres/{genreId}")
    public String getGenrePage(Model model, @PathVariable("genreId") long genreId) throws ServerException {
        LOGGER.info("GenreController get genre page");

        GenreDtoResponse genre = genreService.getGenre(genreId);

        model.addAttribute("genre", genre);

        return "genre";
    }
}
