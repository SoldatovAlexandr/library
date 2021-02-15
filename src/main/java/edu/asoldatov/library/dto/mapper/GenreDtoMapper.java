package edu.asoldatov.library.dto.mapper;

import edu.asoldatov.library.dto.request.GenreDtoRequest;
import edu.asoldatov.library.dto.response.GenreDtoResponse;
import edu.asoldatov.library.model.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class GenreDtoMapper {
    public static final GenreDtoMapper INSTANCE = Mappers.getMapper(GenreDtoMapper.class);

    public abstract GenreDtoResponse toGenreDtoResponse(Genre genre);

    public abstract Genre toGenre(GenreDtoRequest genreDtoRequest);

    public List<GenreDtoResponse> toGenreDtoResponses(Iterable<Genre> genres) {
        List<GenreDtoResponse> responses = new ArrayList<>();

        for (Genre genre : genres) {
            responses.add(toGenreDtoResponse(genre));
        }

        return responses;
    }
}
