package com.JAPKAM.Movieverse.repository;

import com.JAPKAM.Movieverse.entity.MovieSession;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieSessionRepository extends MongoRepository<MovieSession, String> {
    List<MovieSession> findByMovieId(String id);
}
