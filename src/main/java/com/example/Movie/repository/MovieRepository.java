package com.example.Movie.repository;

import com.example.Movie.entity.Movie;
import com.example.Movie.entity.MovieStatus;
import com.example.Movie.entity.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Page<Movie> findByTitleContainingIgnoreCase(
            String title,
            Pageable pageable
    );

    Page<Movie> findByDirectorContainingIgnoreCase(
            String director,
            Pageable pageable
    );

    Page<Movie> findByStarsContaining(
            String star,
            Pageable pageable
    );

    Page<Movie> findByGenreContaining(
            String genre,
            Pageable pageable
    );

    Page<Movie> findByType(
            MovieType type,
            Pageable pageable
    );

    Page<Movie> findByStatus(
            MovieStatus status,
            Pageable pageable
    );

}