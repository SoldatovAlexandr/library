package edu.asoldatov.library.dto.mapper;

import edu.asoldatov.library.dto.request.AuthorDtoRequest;
import edu.asoldatov.library.dto.response.AuthorDtoResponse;
import edu.asoldatov.library.dto.response.BookDtoResponse;
import edu.asoldatov.library.model.Author;
import edu.asoldatov.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class AuthorDtoMapper {
    public static final AuthorDtoMapper INSTANCE = Mappers.getMapper(AuthorDtoMapper.class);

    public abstract Author toAuthor(AuthorDtoRequest authorDtoRequest);

    public abstract List<AuthorDtoResponse> toAuthorDtoResponses(Set<Author> authors);

    public abstract List<AuthorDtoResponse> toAuthorDtoResponses(List<Author> authors);

    public AuthorDtoResponse toAuthorDtoResponse(Author author) {
        return new AuthorDtoResponse(
                author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getPatronymic(),
                author.getYearOfBirth(),
                author.getBiography(),
                author.getBooks().stream().map(this::bookDtoResponseWithoutAuthorsAndGenres).collect(Collectors.toSet())
        );
    }

    public BookDtoResponse bookDtoResponseWithoutAuthorsAndGenres(Book book) {
        return new BookDtoResponse(
                book.getId(),
                book.getName(),
                book.getYearOfPublishing()
        );
    }


}
