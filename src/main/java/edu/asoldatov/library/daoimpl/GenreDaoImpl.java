package edu.asoldatov.library.daoimpl;

import edu.asoldatov.library.dao.GenreDao;
import edu.asoldatov.library.model.Genre;
import edu.asoldatov.library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GenreDaoImpl implements GenreDao {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreDaoImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<Genre> getById(Long genreId) {
        return genreRepository.findById(genreId);
    }

    @Override
    public void update(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void delete(Genre genre) {
        genreRepository.delete(genre);
    }

    @Override
    public void insert(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public Iterable<Genre> getAll() {
        return genreRepository.findAll();
    }
}
