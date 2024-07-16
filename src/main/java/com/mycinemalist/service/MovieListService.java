package com.mycinemalist.service;

import com.mycinemalist.entity.MovieList;

import java.util.List;

public interface MovieListService {
    MovieList createMovieList(MovieList movieList);
    MovieList getMovieListById(String id);
    //List<MovieList> getAllMovieListsCreatedByUser();

    List<MovieList> getAllMovieListsCreatedByUser(String id);

    MovieList updateMovieList(String id, MovieList movieList);
    void deleteMovieList(String id);

    MovieList addMovieToList(String listId, String movieId);

    MovieList removeMovieFromList(String listId, String movieId);

    MovieList shareMovieList(String listId, String userId); // Add method for sharing lists
}
