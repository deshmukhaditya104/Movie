package com.example.Movie.dto.request;

import com.example.Movie.entity.MovieStatus;
import com.example.Movie.entity.MovieType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieRequest {

    @NotBlank
    private String title;

    @NotNull
    private Integer year;

    private String duration;

    private Double rating;

    private Double popularity;

    private List<String> genre;

    private String description;

    private String director;

    private List<String> writers;

    private List<String> stars;

    private String imageUrl;

    private MovieType type;

    private MovieStatus status;
}