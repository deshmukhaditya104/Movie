package com.example.Movie.repository;

import com.example.Movie.entity.Follow;
import com.example.Movie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findByUser(User user);

    List<Follow> findByActorNameIgnoreCase(String actorName);

    Optional<Follow> findByUserAndActorNameIgnoreCase(User user, String actorName);

    boolean existsByUserAndActorNameIgnoreCase(User user, String actorName);

    void deleteByUserAndActorNameIgnoreCase(User user, String actorName);

}