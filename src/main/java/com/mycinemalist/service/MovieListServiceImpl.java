package com.mycinemalist.service;

import com.mycinemalist.entity.MovieList;
import com.mycinemalist.repository.MovieListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieListServiceImpl implements MovieListService {

    @Autowired
    private MovieListRepository movieListRepository;

    @Override
    public MovieList createMovieList(MovieList movieList) {
        return movieListRepository.save(movieList);
    }

    @Override
    public MovieList getMovieListById(String id) {
        return movieListRepository.findById(id).orElse(null);
    }

    @Override
    public List<MovieList> getAllMovieListsCreatedByUser(String id) {
        return movieListRepository.findAllByCreatedByUserId(id);
    }

    @Override
    public MovieList updateMovieList(String id, MovieList movieList) {
        movieList.setId(id);
        return movieListRepository.save(movieList);
    }

    @Override
    public void deleteMovieList(String id) {
        movieListRepository.deleteById(id);
    }

    @Override
    public MovieList addMovieToList(String listId, String movieId) {
        MovieList movieList = movieListRepository.findById(listId).orElseThrow();
        movieList.getMovieIds().add(movieId);
        return movieListRepository.save(movieList);
    }

    @Override
    public MovieList removeMovieFromList(String listId, String movieId) {
        MovieList movieList = movieListRepository.findById(listId).orElseThrow();
        movieList.getMovieIds().remove(movieId);
        return movieListRepository.save(movieList);
    }


    @Override
    public MovieList shareMovieList(String listId, String userId) {
        MovieList movieList = movieListRepository.findById(listId).orElse(null);
        if (movieList != null && !movieList.getSharedWith().contains(userId)) {
            movieList.getSharedWith().add(userId);
            movieListRepository.save(movieList);
        }
        return movieList;
    }
}
