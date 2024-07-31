package com.mycinemalist.service;

import com.mycinemalist.dto.MovieDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieDbService {

    @Value("${tmdb.api.token}")
    private String apiKey;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public List<MovieDTO> searchMovies(String title) {
        try {
            System.out.println("searching for title: " + title);
            String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8.toString());
            String url = String.format("https://api.themoviedb.org/3/search/movie?query=%s&include_adult=false&language=en-US&page=1", encodedTitle);
            System.out.println("Request URL: " + url);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.body());
            JsonNode resultsNode = rootNode.get("results");

            List<MovieDTO> movieDTOs = new ArrayList<>();
            if (resultsNode.isArray()) {
                for (JsonNode movieNode : resultsNode) {
                    MovieDTO movieDTO = MovieDTO.builder()
                            .id(movieNode.get("id").asText())
                            .title(movieNode.get("title").asText())
                            .overview(movieNode.get("overview").asText())
                            .releaseDate(movieNode.get("release_date").asText())
                            .posterPath(movieNode.get("poster_path").asText())
                            .backdropPath(movieNode.get("backdrop_path").asText())
                            .voteAverage(movieNode.get("vote_average").asDouble())
                            .voteCount(movieNode.get("vote_count").asInt())
                            .popularity(movieNode.get("popularity").asDouble())
                            .build();
                    movieDTOs.add(movieDTO);
                    System.out.println("poster path: " + movieDTO.getPosterPath());
                }
            }
            return movieDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
