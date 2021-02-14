package edu.asoldatov.library.dto.mapper;

import edu.asoldatov.library.dto.request.CreateBookDtoRequest;
import edu.asoldatov.library.dto.response.BookDtoResponse;
import edu.asoldatov.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class BookDtoMapper {
    public static final BookDtoMapper INSTANCE = Mappers.getMapper(BookDtoMapper.class);

    public abstract Book toBook(CreateBookDtoRequest request);

    public BookDtoResponse toBookDtoResponse(Book book) {
        return new BookDtoResponse(
                book.getId(),
                book.getName(),
                book.getYearOfPublishing(),
                GenreDtoMapper.INSTANCE.toGenreDtoResponses(book.getGenres()),
                AuthorDtoMapper.INSTANCE.toAuthorDtoResponses(book.getAuthors()),
                UserDtoMapper.INSTANCE.toUserDtoResponse(book.getUser())
        );
    }

    public List<BookDtoResponse> toBooks(List<Book> books) {
        List<BookDtoResponse> responses = new ArrayList<>();

        for (Book book : books) {
            responses.add(toBookDtoResponse(book));
        }

        return responses;
    }
}
