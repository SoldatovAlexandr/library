package edu.asoldatov.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author extends AbstractPersistable<Long> {
    private String firstName;

    private String lastName;

    private String patronymic;

    private Integer yearOfBirth;

    private String biography;

    @Column
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "authors_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books = new ArrayList<>();

    public Author(long id, String firstName, String lastName, String patronymic, Integer yearOfBirth, String biography, List<Book> books) {
        this(firstName, lastName, patronymic, yearOfBirth, biography, books);
        this.setId(id);
    }
}
