package com.mycinemalist.repository;

import com.mycinemalist.entity.MovieList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieListRepository extends MongoRepository<MovieList, String> {
}

