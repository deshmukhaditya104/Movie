package com.example.Movie.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowRequest {

    @NotBlank(message = "Actor name is required")
    private String actorName;
}
