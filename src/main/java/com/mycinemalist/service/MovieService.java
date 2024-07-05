package com.mycinemalist.service;

import com.mycinemalist.entity.Movie;

import java.util.List;

public interface MovieService {
    Movie createMovie(Movie movie);
    Movie getMovieById(String id);
    List<Movie> getAllMovies();
    Movie updateMovie(String id, Movie movie);
    void deleteMovie(String id);
}
