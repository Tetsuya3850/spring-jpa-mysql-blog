package com.example.blog.Repository;


import com.example.blog.Entity.Genre;
import com.example.blog.Entity.GenreDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreDetailsRepository extends JpaRepository<GenreDetails, Long> {

    Optional<GenreDetails> findByGenre(Genre genre);
}
