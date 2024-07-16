package com.mycinemalist.repository;

import com.mycinemalist.entity.MovieList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieListRepository extends MongoRepository<MovieList, String> {

    List<MovieList> findAllByCreatedByUserId(String id);

}

