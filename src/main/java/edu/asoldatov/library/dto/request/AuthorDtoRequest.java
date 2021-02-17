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
public class AuthorDtoRequest {
    @NotBlank(message = "{blank.firstname}")
    private String firstName;

    @NotBlank(message = "{blank.lastname}")
    private String lastName;

    @NotBlank(message = "{blank.patronymic}")
    private String patronymic;

    @Max(value = 2021, message = "{invalid.year}")
    @Min(value = 0, message = "{negative.year}")
    @NotNull(message = "{blank.year}")
    private Integer yearOfBirth;

    @NotBlank(message = "{blank.biography}")
    private String biography;
}
