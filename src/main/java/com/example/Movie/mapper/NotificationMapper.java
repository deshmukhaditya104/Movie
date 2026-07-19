package com.example.Movie.mapper;

import com.example.Movie.dto.response.NotificationResponse;
import com.example.Movie.entity.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationResponse toResponse(Notification notification);

}