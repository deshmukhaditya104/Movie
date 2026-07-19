package com.example.Movie.controller;

import com.example.Movie.dto.response.MovieResponse;
import com.example.Movie.entity.MovieStatus;
import com.example.Movie.entity.MovieType;
import com.example.Movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public Page<MovieResponse> getAllMovies(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return movieService.getAllMovies(pageable);
    }

    @GetMapping("/{id}")
    public MovieResponse getMovieById(@PathVariable Long id) {

        return movieService.getMovieById(id);
    }

    @GetMapping("/search")
    public Page<MovieResponse> searchMovies(

            @RequestParam(required = false) String title,

            @RequestParam(required = false) String director,

            @RequestParam(required = false) String star,

            @RequestParam(required = false) String genre,

            @RequestParam(required = false) MovieType type,

            @RequestParam(required = false) MovieStatus status,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return movieService.searchMovies(
                title,
                director,
                star,
                genre,
                type,
                status,
                pageable
        );
    }

}
