package edu.asoldatov.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private int yearOfPublishing;

    @ManyToOne
    private User user;

    @Column
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private Set<Genre> genres;

    @Column
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Author> authors;

    public Book(String name, int yearOfPublishing) {
        this.name = name;
        this.yearOfPublishing = yearOfPublishing;
    }
}
