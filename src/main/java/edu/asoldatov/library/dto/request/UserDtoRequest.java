package edu.asoldatov.library.dto.request;


import edu.asoldatov.library.validator.ConfirmPasswords;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfirmPasswords(password = "password", confirmPassword = "passwordConfirm")
public class UserDtoRequest {

    @Size(min = 2, message = "{short.length}")
    private String username;

    @Size(min = 2, message = "{short.length}")
    private String password;

    private String passwordConfirm;

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
}
