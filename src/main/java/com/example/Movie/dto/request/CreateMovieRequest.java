package com.example.Movie.dto.request;

import com.example.Movie.entity.MovieStatus;
import com.example.Movie.entity.MovieType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMovieRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Year is required")
    @Min(value = 1888)
    private Integer year;

    @NotBlank(message = "Duration is required")
    private String duration;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    private Double rating;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    private Double popularity;

    @NotEmpty(message = "Genre cannot be empty")
    private List<String> genre;

    @NotBlank
    private String description;

    @NotBlank
    private String director;

    @NotEmpty
    private List<String> writers;

    @NotEmpty
    private List<String> stars;

    @NotBlank
    private String imageUrl;

    @NotNull
    private MovieType type;

    @NotNull
    private MovieStatus status;
}