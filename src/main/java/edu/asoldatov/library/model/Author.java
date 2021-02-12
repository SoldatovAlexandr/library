package edu.asoldatov.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private Integer yearOfBirth;

    private String biography;

    @Column
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Book> books;

    //private User user;

    public Author(String firstName, String lastName, String patronymic, int yearOfBirth, String biography) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.yearOfBirth = yearOfBirth;
        this.biography = biography;
    }
}
