package edu.asoldatov.library.dto.request;

import edu.asoldatov.library.validator.UpdateAuthor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@UpdateAuthor(first = "firstName", second = "lastName", third = "patronymic", forth = "yearOfBirth", fifth = "biography")
public class UpdateAuthorDtoRequest {
    private String firstName;

    private String lastName;

    private String patronymic;

    private Integer yearOfBirth;

    private String biography;
}
