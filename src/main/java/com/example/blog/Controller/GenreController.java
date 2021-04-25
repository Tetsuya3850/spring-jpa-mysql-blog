package com.example.blog.Controller;

import com.example.blog.Controller.Resource.BlogRequest;
import com.example.blog.Controller.Resource.GenreRequest;
import com.example.blog.Entity.Genre;
import com.example.blog.Entity.GenreDetails;
import com.example.blog.Service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @PostMapping("")
    Genre saveGenre(
            @Valid @RequestBody GenreRequest genreRequest
    ) {
        return genreService.saveGenre(genreRequest);
    }

    @GetMapping("/{genreId}")
    Genre getGenre(
            @PathVariable Long genreId
    ) {
        return genreService.getGenre(genreId);
    }

    @GetMapping("/details/{genreId}")
    GenreDetails getGenreWithDetails(
            @PathVariable Long genreId
    ) {
        return genreService.getGenreWithDetails(genreId);
    }
}
