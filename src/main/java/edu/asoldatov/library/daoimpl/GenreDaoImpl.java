package edu.asoldatov.library.daoimpl;

import edu.asoldatov.library.dao.GenreDao;
import edu.asoldatov.library.model.Genre;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreDaoImpl implements GenreDao {
    @Override
    public Genre getById(Long genreId) {
        return null;
    }

    @Override
    public void update(Genre genre) {

    }

    @Override
    public void delete(Genre genre) {

    }

    @Override
    public void insert(Genre genre) {

    }

    @Override
    public List<Genre> getAll() {
        return null;
    }
}
