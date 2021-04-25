package com.example.blog.Service;

import com.example.blog.Controller.Resource.GenreRequest;
import com.example.blog.Entity.Genre;
import com.example.blog.Entity.GenreDetails;
import com.example.blog.Repository.GenreDetailsRepository;
import com.example.blog.Repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreDetailsRepository genreDetailsRepository;

    public Genre saveGenre(
            GenreRequest genreRequest
    ) {
        Genre newGenre = new Genre(
                genreRequest.getName()
        );
        Genre genre = genreRepository.save(newGenre);
        GenreDetails genreDetails = new GenreDetails(
                genreRequest.getDescription(),
                genre
        );
        genreDetailsRepository.save(genreDetails);
        return genre;
    }

    public Genre getGenre(
            Long genreId
    ) {
        return genreRepository
                .findById(genreId)
                .orElseThrow(() -> new RuntimeException());
    }

    public GenreDetails getGenreWithDetails(
            Long genreId
    ) {
        Genre genre = genreRepository
                .findById(genreId)
                .orElseThrow(() -> new RuntimeException());
        return genreDetailsRepository.findByGenre(genre);
    }
}
