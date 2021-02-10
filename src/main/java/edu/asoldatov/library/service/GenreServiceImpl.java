package edu.asoldatov.library.service;

import edu.asoldatov.library.dao.BookDao;
import edu.asoldatov.library.dao.GenreDao;
import edu.asoldatov.library.dto.mapper.GenreDtoMapper;
import edu.asoldatov.library.dto.request.CreateGenreDtoRequest;
import edu.asoldatov.library.dto.response.EmptyDtoResponse;
import edu.asoldatov.library.dto.response.GenreDtoResponse;
import edu.asoldatov.library.erroritem.exception.ServerException;
import edu.asoldatov.library.model.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl extends ServiceBase implements GenreService {

    protected GenreServiceImpl(BookDao bookDao, GenreDao genreDao) {
        super(bookDao, genreDao);
    }

    @Override
    public GenreDtoResponse createGenre(CreateGenreDtoRequest request) {
        Genre genre = GenreDtoMapper.INSTANCE.toGenre(request);

        genreDao.insert(genre);

        return GenreDtoMapper.INSTANCE.toGenreDtoResponse(genre);
    }

    @Override
    public GenreDtoResponse getGenre(long genreId) throws ServerException {
        Genre genre = getGenreById(genreId);

        return GenreDtoMapper.INSTANCE.toGenreDtoResponse(genre);
    }

    @Override
    public List<GenreDtoResponse> getGenres() {
        List<Genre> genres= genreDao.getAll();

        return GenreDtoMapper.INSTANCE.toGenreDtoResponses(genres);
    }

    @Override
    public GenreDtoResponse updateGenre(CreateGenreDtoRequest request, long genreId) throws ServerException {
        Genre genre = getGenreById(genreId);

        String name = request.getName();

        genre.setName(name);

        genreDao.update(genre);

        return GenreDtoMapper.INSTANCE.toGenreDtoResponse(genre);
    }

    @Override
    public EmptyDtoResponse deleteGenre(long genreId) throws ServerException {
        Genre genre = getGenreById(genreId);

        genreDao.delete(genre);

        return new EmptyDtoResponse();
    }
}
