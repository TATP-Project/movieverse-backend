package com.JAPKAM.Movieverse.repository;

import com.JAPKAM.Movieverse.entity.Timeslot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeslotMongoRepository extends MongoRepository<Timeslot, String> {
}
