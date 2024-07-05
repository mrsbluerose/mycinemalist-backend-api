package com.mycinemalist.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "movies")
public class Movie {
    @Id
    private String id;
    private String title;
    private String description;
    private String length;
    private String date;
    private List<String> genres;
    private List<String> actors;
    private List<String> directors;
    private String posterUrl;
}

