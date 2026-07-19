package com.example.Movie.controller;

import com.example.Movie.dto.response.FollowResponse;
import com.example.Movie.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{actorName}")
    public FollowResponse followActor(@PathVariable String actorName) {
        return followService.followActor(actorName);
    }

    @DeleteMapping("/{actorName}")
    public String unfollowActor(@PathVariable String actorName) {

        followService.unfollowActor(actorName);

        return "Actor unfollowed successfully";
    }

    @GetMapping
    public List<FollowResponse> getFollowedActors() {
        return followService.getFollowedActors();
    }
}