package com.example.Movie.mapper;

import com.example.Movie.dto.request.CreateMovieRequest;
import com.example.Movie.dto.response.MovieResponse;
import com.example.Movie.dto.request.UpdateMovieRequest;
import com.example.Movie.entity.Movie;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    Movie toEntity(CreateMovieRequest request);

    MovieResponse toResponse(Movie movie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMovieFromRequest(UpdateMovieRequest request,
                                @MappingTarget Movie movie);

}