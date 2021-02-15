package edu.asoldatov.library.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookDtoRequest {

    @NotNull(message = "NAME_NOT_SET")
    private String name;

    @NotNull(message = "YEAR_OF_PUBLISHING_NOT_SET")
    private Integer yearOfPublishing;

    @NotNull(message = "GENRE_ID_NOT_SET")
    private Long genreId;
}
