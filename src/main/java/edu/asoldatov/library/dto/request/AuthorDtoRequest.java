package edu.asoldatov.library.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDtoRequest {
    @NotNull(message = "FIRST_NAME_NOT_SET")
    private String firstName;

    @NotNull(message = "LAST_NAME_NOT_SET")
    private String lastName;

    @NotNull(message = "PATRONYMIC_NOT_SET")
    private String patronymic;

    @NotNull(message = "YEAR_OF_BIRTH_NOT_SET")
    private Integer yearOfBirth;

    @NotNull(message = "BIOGRAPHY_NOT_SET")
    private String biography;
}
