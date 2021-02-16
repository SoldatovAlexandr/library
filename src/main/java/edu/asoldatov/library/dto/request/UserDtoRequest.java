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
    @Size(min = 2, message = "Не меньше 5 знаков")
    private String username;

    @Size(min = 2, message = "Не меньше 5 знаков")
    private String password;

    private String passwordConfirm;

    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;

    @NotBlank(message = "Отчество не может быть пустым")
    private String patronymic;

    @Max(value = 2021, message = "Не корректный год рождения")
    @Min(value = 0, message = "Год рождения не может быть отрицательным")
    @NotNull(message = "Год рождения не может быть пустым")
    private Integer yearOfBirth;
}
