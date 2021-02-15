package edu.asoldatov.library.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.asoldatov.library.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDtoResponse {
    private long id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private int yearOfBirth;

    private String username;

    private List<Role> roles;
}
