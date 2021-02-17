package edu.asoldatov.library.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDtoRequest {

    @NotBlank(message = "{blank.book.name}")
    private String name;

    @Max(value = 2021, message = "{invalid.year}")
    @Min(value = 0, message = "{negative.year}")
    @NotNull(message = "{blank.year}")
    private Integer yearOfPublishing;

    @NotNull(message = "{blank.id}")
    private Long genreId;
}
