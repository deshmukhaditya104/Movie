package com.example.Movie.service.impl;

import com.example.Movie.dto.request.CreateMovieRequest;
import com.example.Movie.dto.response.MovieResponse;
import com.example.Movie.dto.request.UpdateMovieRequest;
import com.example.Movie.entity.Movie;
import com.example.Movie.entity.MovieStatus;
import com.example.Movie.entity.MovieType;
import com.example.Movie.mapper.MovieMapper;
import com.example.Movie.repository.MovieRepository;
import com.example.Movie.service.MovieService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieMapper movieMapper;

    @Override
    public MovieResponse createMovie(CreateMovieRequest request) {

        Movie movie = movieMapper.toEntity(request);

        Movie savedMovie = movieRepository.save(movie);

        log.info("Movie {} created successfully", savedMovie.getTitle());

        return movieMapper.toResponse(savedMovie);
    }

    @Override
    public MovieResponse updateMovie(Long movieId,
                                     UpdateMovieRequest request) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Movie not found"));

        movieMapper.updateMovieFromRequest(request, movie);

        Movie updatedMovie = movieRepository.save(movie);

        log.info("Movie {} updated", updatedMovie.getTitle());

        return movieMapper.toResponse(updatedMovie);
    }

    @Override
    public void deleteMovie(Long movieId) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Movie not found"));

        movieRepository.delete(movie);

        log.info("Movie {} deleted", movie.getTitle());
    }

    @Override
    public MovieResponse getMovieById(Long movieId) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Movie not found"));

        return movieMapper.toResponse(movie);
    }

    @Override
    public Page<MovieResponse> getAllMovies(Pageable pageable) {

        return movieRepository.findAll(pageable)
                .map(movieMapper::toResponse);
    }

    @Override
    public Page<MovieResponse> searchMovies(
            String title,
            String director,
            String star,
            String genre,
            MovieType type,
            MovieStatus status,
            Pageable pageable) {

        if (title != null) {
            return movieRepository
                    .findByTitleContainingIgnoreCase(title, pageable)
                    .map(movieMapper::toResponse);
        }

        if (director != null) {
            return movieRepository
                    .findByDirectorContainingIgnoreCase(director, pageable)
                    .map(movieMapper::toResponse);
        }

        if (star != null) {
            return movieRepository
                    .findByStarsContaining(star, pageable)
                    .map(movieMapper::toResponse);
        }

        if (genre != null) {
            return movieRepository
                    .findByGenreContaining(genre, pageable)
                    .map(movieMapper::toResponse);
        }

        if (type != null) {
            return movieRepository
                    .findByType(type, pageable)
                    .map(movieMapper::toResponse);
        }

        if (status != null) {
            return movieRepository
                    .findByStatus(status, pageable)
                    .map(movieMapper::toResponse);
        }

        return movieRepository.findAll(pageable)
                .map(movieMapper::toResponse);
    }

    @Override
    public MovieResponse launchMovie(Long movieId) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Movie not found"));

        movie.setStatus(MovieStatus.PUBLISHED);

        Movie updatedMovie = movieRepository.save(movie);

        /*
           Notification module

           notificationService.notifyFollowers(updatedMovie);
        */

        log.info("{} launched successfully", updatedMovie.getTitle());

        return movieMapper.toResponse(updatedMovie);
    }

}
