package com.example.Movie.controller;

import com.example.Movie.dto.request.CreateMovieRequest;
import com.example.Movie.dto.response.MovieResponse;
import com.example.Movie.dto.request.UpdateMovieRequest;
import com.example.Movie.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/movies")
@RequiredArgsConstructor
public class AdminMovieController {

    private final MovieService movieService = null;

    @PostMapping
    public MovieResponse createMovie(
            @Valid @RequestBody CreateMovieRequest request) {

        return movieService.createMovie(request);
    }

    @PutMapping("/{id}")
    public MovieResponse updateMovie(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMovieRequest request) {

        return movieService.updateMovie(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable Long id) {

        movieService.deleteMovie(id);

        return "Movie deleted successfully";
    }

    @PostMapping("/{id}/launch")
    public MovieResponse launchMovie(@PathVariable Long id) {

        return movieService.launchMovie(id);
    }

}