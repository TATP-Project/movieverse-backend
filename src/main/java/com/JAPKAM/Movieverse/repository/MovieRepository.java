package com.JAPKAM.Movieverse.repository;

import com.JAPKAM.Movieverse.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie,String> {

}
