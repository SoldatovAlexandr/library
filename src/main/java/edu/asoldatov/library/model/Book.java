package edu.asoldatov.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//TODO extends AbstractPersistable<Long>
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

    @Column
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Genre> genres;

    @Column
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Author> authors;

    public Book(String name, int yearOfPublishing) {
        this.name = name;
        this.yearOfPublishing = yearOfPublishing;
    }
}
