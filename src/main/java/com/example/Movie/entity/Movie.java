package com.example.Movie.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private String duration;

    private Double rating;

    private Double popularity;

    @ElementCollection
    @CollectionTable(name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre")
    private List<String> genre;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String director;

    @ElementCollection
    @CollectionTable(name = "movie_writers",
            joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "writer")
    private List<String> writers;

    @ElementCollection
    @CollectionTable(name = "movie_stars",
            joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "star")
    private List<String> stars;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private MovieType type;

    @Enumerated(EnumType.STRING)
    private MovieStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "movie")
    private List<Notification> notifications = new ArrayList<>();
}