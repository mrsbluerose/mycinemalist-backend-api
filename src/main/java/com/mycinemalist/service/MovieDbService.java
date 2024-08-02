package com.mycinemalist.service;

import com.mycinemalist.entity.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieDbService {

    @Value("${tmdb.api.token}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public Movie getMovieDetails(String movieId) {
        String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
        return restTemplate.getForObject(url, Movie.class);
    }
}
