package com.example.blog.Repository;


import com.example.blog.Entity.Genre;
import com.example.blog.Entity.GenreDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreDetailsRepository extends JpaRepository<GenreDetails, Long> {

    GenreDetails findByGenre(Genre genre);
}
