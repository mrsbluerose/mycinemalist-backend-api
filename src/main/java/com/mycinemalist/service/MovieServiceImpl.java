package com.mycinemalist.service;

import com.mycinemalist.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDbService movieDbService;

    @Override
    public List<MovieDTO> searchMovies(String title) {
        return movieDbService.searchMovies(title);
    }
}
