package com.example.blog.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "genre_details")
public class GenreDetails {

    @Id
    private Long id;

    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Genre genre;

    public GenreDetails (
            String description,
            Genre genre
    ) {
        this.description = description;
        this.genre = genre;
    }
}
