package com.example.Movie.mapper;

import com.example.Movie.dto.response.FollowResponse;
import com.example.Movie.entity.Follow;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FollowMapper {

    FollowResponse toResponse(Follow follow);

}
