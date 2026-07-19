package com.example.Movie.service;

import com.example.Movie.dto.response.NotificationResponse;
import com.example.Movie.dto.response.UnreadCountResponse;
import com.example.Movie.entity.Movie;

import java.util.List;

public interface NotificationService {

    void notifyFollowers(Movie movie);

    List<NotificationResponse> getNotifications();

    NotificationResponse markAsRead(Long notificationId);

    void markAllAsRead();

    UnreadCountResponse getUnreadCount();
}