package com.JAPKAM.Movieverse.repository;

import com.JAPKAM.Movieverse.entity.MovieSession;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieSessionRepository extends MongoRepository<MovieSession, String> {
}
