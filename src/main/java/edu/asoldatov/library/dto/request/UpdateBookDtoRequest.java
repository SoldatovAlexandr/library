package edu.asoldatov.library.dto.request;

import edu.asoldatov.library.validator.UpdateBook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@UpdateBook(first = "name", second = "yearOfPublishing", third = "genreId")
public class UpdateBookDtoRequest {
    private String name;

    private int yearOfPublishing;

    private long genreId;
}
