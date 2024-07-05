package com.mycinemalist.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "movielists")
public class MovieList {

    @Id
    private String id;
    private String name;
    private String createdByUserId;
    private List<String> movieIds;
    private List<String> sharedWith;  // List of user IDs with whom the list is shared

}
