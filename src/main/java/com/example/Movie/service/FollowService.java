package com.example.Movie.service;

import com.example.Movie.dto.response.FollowResponse;

import java.util.List;

public interface FollowService {

    FollowResponse followActor(String actorName);

    void unfollowActor(String actorName);

    List<FollowResponse> getFollowedActors();
}
