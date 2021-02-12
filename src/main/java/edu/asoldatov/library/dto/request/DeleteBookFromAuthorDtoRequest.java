package edu.asoldatov.library.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBookFromAuthorDtoRequest {
    @NotNull(message = "BOOK_ID_NOT_SET")
    private Integer bookId;
}
