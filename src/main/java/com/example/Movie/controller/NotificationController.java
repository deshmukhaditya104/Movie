package com.example.Movie.controller;

import com.example.Movie.dto.response.NotificationResponse;
import com.example.Movie.dto.response.UnreadCountResponse;
import com.example.Movie.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationResponse> getNotifications() {

        return notificationService.getNotifications();
    }

    @PutMapping("/{id}/read")
    public NotificationResponse markAsRead(
            @PathVariable Long id) {

        return notificationService.markAsRead(id);
    }

    @PutMapping("/read-all")
    public String markAllAsRead() {

        notificationService.markAllAsRead();

        return "All notifications marked as read";
    }

    @GetMapping("/unread-count")
    public UnreadCountResponse getUnreadCount() {

        return notificationService.getUnreadCount();
    }
}