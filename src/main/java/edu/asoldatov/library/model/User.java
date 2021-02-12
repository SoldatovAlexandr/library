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
public class User {
    @Id
    private long id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private int yearOfBirth;

    @Column
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Book> books;
}
