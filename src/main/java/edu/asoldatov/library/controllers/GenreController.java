package edu.asoldatov.library.controllers;

import edu.asoldatov.library.dto.request.GenreDtoRequest;
import edu.asoldatov.library.dto.response.GenreDtoResponse;
import edu.asoldatov.library.exception.ServerException;
import edu.asoldatov.library.service.GenreService;
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
public class GenreController {

    private final GenreService genreService;

    @RequestMapping(path = "/genres", method = RequestMethod.GET)
    public String getGenresPage(Model model) {
        log.info("GenreController get genres page");

        List<GenreDtoResponse> genres = genreService.getGenres();

        model.addAttribute("genres", genres);

        model.addAttribute("genre", new GenreDtoRequest());

        return "genres";
    }

    @RequestMapping(path = "/genres", method = RequestMethod.POST)
    public String addGenre(@ModelAttribute(name = "genre") @Valid GenreDtoRequest genreDtoRequest,
                           BindingResult bindingResult,
                           Model model) {
        log.info("GenreController add new genre");

        if (!bindingResult.hasErrors()) {
            genreService.createGenre(genreDtoRequest);
        }

        List<GenreDtoResponse> genres = genreService.getGenres();

        model.addAttribute("genres", genres);

        return "genres";
    }

    @RequestMapping(path = "/genres/{genreId}", method = RequestMethod.GET)
    public String getGenrePage(Model model, @PathVariable("genreId") long genreId) throws ServerException {
        log.info("GenreController get genre page");

        GenreDtoResponse genre = genreService.getGenre(genreId);

        model.addAttribute("genre", genre);

        return "genre";
    }
}
