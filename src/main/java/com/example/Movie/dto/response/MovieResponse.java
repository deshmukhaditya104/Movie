package com.example.Movie.dto.response;

import com.example.Movie.entity.MovieStatus;
import com.example.Movie.entity.MovieType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class MovieResponse {

    private Long id;

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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}