package edu.asoldatov.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends AbstractPersistable<Long> {
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

    public Book(long id, String name, int yearOfPublishing, User user, Set<Genre> genres, Set<Author> authors) {
        this(name, yearOfPublishing, user, genres, authors);
        this.setId(id);
    }
}
