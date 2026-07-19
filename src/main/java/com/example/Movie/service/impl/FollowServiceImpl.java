package com.example.Movie.service.impl;

import com.example.Movie.dto.response.FollowResponse;
import com.example.Movie.entity.Follow;
import com.example.Movie.entity.User;
import com.example.Movie.exception.DuplicateResourceException;
import com.example.Movie.mapper.FollowMapper;
import com.example.Movie.repository.FollowRepository;
import com.example.Movie.repository.UserRepository;
import com.example.Movie.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final FollowMapper followMapper;

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    @Transactional
    public FollowResponse followActor(String actorName) {

        User user = getCurrentUser();

        if (followRepository.existsByUserAndActorNameIgnoreCase(user, actorName)) {
            throw new DuplicateResourceException("Already following this actor");
        }

        Follow follow = Follow.builder()
                .user(user)
                .actorName(actorName)
                .build();

        Follow saved = followRepository.save(follow);

        log.info("{} followed {}", user.getEmail(), actorName);

        return followMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void unfollowActor(String actorName) {

        User user = getCurrentUser();

        followRepository.deleteByUserAndActorNameIgnoreCase(user, actorName);

        log.info("{} unfollowed {}", user.getEmail(), actorName);
    }

    @Override
    public List<FollowResponse> getFollowedActors() {

        User user = getCurrentUser();

        return followRepository.findByUser(user)
                .stream()
                .map(followMapper::toResponse)
                .toList();
    }
}
