package edu.asoldatov.library.dto.mapper;

import edu.asoldatov.library.dto.request.CreateAuthorDtoRequest;
import edu.asoldatov.library.dto.response.AuthorDtoResponse;
import edu.asoldatov.library.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class AuthorDtoMapper {
    public static final AuthorDtoMapper INSTANCE = Mappers.getMapper(AuthorDtoMapper.class);

    public abstract Author toAuthor(CreateAuthorDtoRequest request);

    public abstract AuthorDtoResponse toAuthorDtoResponse(Author author);
}
