package edu.asoldatov.library.model;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Builder(toBuilder = true)
public class Genre extends AbstractPersistable<Long> {

    private String name;

    public Genre(String name) {
        this(0L, name);
    }

    public Genre(long id, String name) {
        this.setId(id);
        this.name = name;
    }
}
