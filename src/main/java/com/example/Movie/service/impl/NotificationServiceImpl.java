package com.example.Movie.service.impl;

import com.example.Movie.dto.response.NotificationResponse;
import com.example.Movie.dto.response.UnreadCountResponse;
import com.example.Movie.entity.Follow;
import com.example.Movie.entity.Movie;
import com.example.Movie.entity.Notification;
import com.example.Movie.entity.User;
import com.example.Movie.mapper.NotificationMapper;
import com.example.Movie.repository.FollowRepository;
import com.example.Movie.repository.NotificationRepository;
import com.example.Movie.repository.UserRepository;
import com.example.Movie.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final NotificationMapper notificationMapper;

    private User getCurrentUser() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    @Transactional
    public void notifyFollowers(Movie movie) {

        for (String actor : movie.getStars()) {

            List<Follow> follows =
                    followRepository.findByActorNameIgnoreCase(actor);

            for (Follow follow : follows) {

                Notification notification = Notification.builder()
                        .user(follow.getUser())
                        .movie(movie)
                        .actorName(actor)
                        .message(movie.getTitle() +
                                " starring " +
                                actor +
                                " is now available!")
                        .isRead(false)
                        .build();

                notificationRepository.save(notification);
            }
        }
    }

    @Override
    public List<NotificationResponse> getNotifications() {

        User user = getCurrentUser();

        return notificationRepository
                .findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(notificationMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public NotificationResponse markAsRead(Long notificationId) {

        Notification notification =
                notificationRepository.findById(notificationId)
                        .orElseThrow(() ->
                                new RuntimeException("Notification not found"));

        notification.setRead(true);

        notificationRepository.save(notification);

        return notificationMapper.toResponse(notification);
    }

    @Override
    @Transactional
    public void markAllAsRead() {

        User user = getCurrentUser();

        List<Notification> notifications =
                notificationRepository.findByUserAndIsReadFalseOrderByCreatedAtDesc(user);

        notifications.forEach(n -> n.setRead(true));

        notificationRepository.saveAll(notifications);
    }

    @Override
    public UnreadCountResponse getUnreadCount() {

        User user = getCurrentUser();

        long count =
                notificationRepository.countByUserAndIsReadFalse(user);

        return new UnreadCountResponse(count);
    }
}