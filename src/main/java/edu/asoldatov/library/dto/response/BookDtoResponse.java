package edu.asoldatov.library.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDtoResponse {
    private long id;

    private String name;

    private int yearOfPublishing;

    private List<GenreDtoResponse> genres;

    private List<AuthorDtoResponse> authors;

    private UserDtoResponse user;

    public BookDtoResponse(long id, String name, int yearOfPublishing) {
        this(id, name, yearOfPublishing, new ArrayList<>(), new ArrayList<>(), null);
    }
}
