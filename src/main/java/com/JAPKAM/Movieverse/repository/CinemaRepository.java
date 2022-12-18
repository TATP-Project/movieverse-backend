package com.JAPKAM.Movieverse.repository;

import com.JAPKAM.Movieverse.entity.Cinema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends MongoRepository<Cinema, String> {
}
