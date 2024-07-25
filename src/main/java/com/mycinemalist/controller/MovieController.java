package com.mycinemalist.controller;

import com.mycinemalist.dto.MovieDTO;
import com.mycinemalist.service.MovieDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieDbService movieDbService;

    @GetMapping("/search")
    public List<MovieDTO> searchMovies(@RequestParam String title) {
        return movieDbService.searchMovies(title);
    }
}

