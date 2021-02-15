package edu.asoldatov.library.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteAuthorFromBookDtoRequest {
    @NotNull(message = "AUTHOR_ID_NOT_SET")
    private Long deleteAuthorId;
}
