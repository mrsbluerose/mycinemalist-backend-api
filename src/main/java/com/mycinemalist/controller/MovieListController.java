package com.mycinemalist.controller;

import com.mycinemalist.entity.MovieList;
import com.mycinemalist.service.MovieListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movielists")
public class MovieListController {

    @Autowired
    private MovieListService movieListService;

    @PostMapping
    public MovieList createMovieList(@RequestBody MovieList movieList) {
        return movieListService.createMovieList(movieList);
    }

//    @GetMapping("/{id}")
//    public MovieList getMovieListById(@PathVariable String id) {
//        return movieListService.getMovieListById(id);
//    }

    @GetMapping("/{userId}")
    public List<MovieList> getAllMovieListsCreatedByUser(@PathVariable String userId) {
        return movieListService.getAllMovieListsCreatedByUser(userId);
    }

    @PutMapping("/{id}")
    public MovieList updateMovieList(@PathVariable String id, @RequestBody MovieList movieList) {
        movieList.setId(id);
        return movieListService.updateMovieList(id, movieList);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieList(@PathVariable String id) {
        movieListService.deleteMovieList(id);
    }

    @PostMapping("/{listId}/share/{userId}")
    public MovieList shareMovieList(@PathVariable String listId, @PathVariable String userId) {
        return movieListService.shareMovieList(listId, userId);
    }

    // Add these methods to your existing MovieListController class

    @PostMapping("/{listId}/movies")
    public MovieList addMovieToList(@PathVariable String listId, @RequestBody String movieId) {
        return movieListService.addMovieToList(listId, movieId);
    }

    @DeleteMapping("/{listId}/movies/{movieId}")
    public MovieList removeMovieFromList(@PathVariable String listId, @PathVariable String movieId) {
        return movieListService.removeMovieFromList(listId, movieId);
    }

}
