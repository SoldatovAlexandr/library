package edu.asoldatov.library.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDtoResponse {
    private long id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private int yearOfBirth;

    private String biography;

    private Set<BookDtoResponse> books;
}
