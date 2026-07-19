package com.example.Movie.service;

import com.example.Movie.dto.request.CreateMovieRequest;
import com.example.Movie.dto.response.MovieResponse;
import com.example.Movie.dto.request.UpdateMovieRequest;
import com.example.Movie.entity.MovieStatus;
import com.example.Movie.entity.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {

    MovieResponse createMovie(CreateMovieRequest request);

    MovieResponse updateMovie(Long movieId, UpdateMovieRequest request);

    void deleteMovie(Long movieId);

    MovieResponse getMovieById(Long movieId);

    Page<MovieResponse> getAllMovies(Pageable pageable);

    Page<MovieResponse> searchMovies(
            String title,
            String director,
            String star,
            String genre,
            MovieType type,
            MovieStatus status,
            Pageable pageable
    );

    MovieResponse launchMovie(Long movieId);

}