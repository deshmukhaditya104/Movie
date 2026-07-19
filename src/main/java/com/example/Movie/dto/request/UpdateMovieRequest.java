package com.example.Movie.dto.request;

import com.example.Movie.entity.MovieStatus;
import com.example.Movie.entity.MovieType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMovieRequest {

    private String title;

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
