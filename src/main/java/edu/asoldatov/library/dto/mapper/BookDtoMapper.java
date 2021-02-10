package edu.asoldatov.library.dto.mapper;

import edu.asoldatov.library.dto.request.CreateBookDtoRequest;
import edu.asoldatov.library.dto.response.BookDtoResponse;
import edu.asoldatov.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public class BookDtoMapper {
    public static final BookDtoMapper INSTANCE = Mappers.getMapper(BookDtoMapper.class);

    public Book toBook(CreateBookDtoRequest request) {
        return new Book(
                request.getName(),
                request.getYearOfPublishing()
        );
    }

    public BookDtoResponse toBookDtoResponse(Book book) {
        return new BookDtoResponse(
                book.getId(),
                book.getName(),
                book.getYearOfPublishing(),
                book.getGenre().getName()
        );
    }
}
