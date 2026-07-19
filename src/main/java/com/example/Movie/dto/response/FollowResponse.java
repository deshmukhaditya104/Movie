package com.example.Movie.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowResponse {

    private Long id;

    private String actorName;
}