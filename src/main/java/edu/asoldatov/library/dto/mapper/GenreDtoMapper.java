package edu.asoldatov.library.dto.mapper;

import edu.asoldatov.library.dto.request.CreateGenreDtoRequest;
import edu.asoldatov.library.dto.response.GenreDtoResponse;
import edu.asoldatov.library.model.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public class GenreDtoMapper {
    public static final GenreDtoMapper INSTANCE = Mappers.getMapper(GenreDtoMapper.class);

    public GenreDtoResponse toGenreDtoResponse(Genre genre) {
        return new GenreDtoResponse(
                genre.getId(),
                genre.getName()
        );
    }

    public Genre toGenre(CreateGenreDtoRequest request) {
        return new Genre(
                request.getName()
        );
    }

    public List<GenreDtoResponse> toGenreDtoResponses(List<Genre> genres) {
        List<GenreDtoResponse> responses = new ArrayList<>();

        for (Genre genre : genres) {
            responses.add(toGenreDtoResponse(genre));
        }

        return responses;
    }
}
