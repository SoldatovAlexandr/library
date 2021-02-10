package edu.asoldatov.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private long id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private int yearOfBirth;

    private String biography;

    private List<Book> books;

    private User user;
}
