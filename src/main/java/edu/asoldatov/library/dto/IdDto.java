package edu.asoldatov.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdDto {
    @NotNull(message = "ID_NOT_SET")
    private Long id;
}
