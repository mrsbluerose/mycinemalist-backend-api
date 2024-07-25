package com.mycinemalist.service;

import com.mycinemalist.dto.MovieDTO;

import java.util.List;

public interface MovieService {
    List<MovieDTO> searchMovies(String title);
}
