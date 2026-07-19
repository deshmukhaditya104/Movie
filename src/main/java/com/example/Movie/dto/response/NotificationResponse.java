package com.example.Movie.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationResponse {

    private Long id;

    private Long movieId;

    private String movieTitle;

    private String actorName;

    private String message;

    private boolean read;

    private LocalDateTime createdAt;
}