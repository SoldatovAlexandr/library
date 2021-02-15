package edu.asoldatov.library.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDtoRequest {
    @NotNull(message = "NAME_NOT_SET")
    private String name;
}
