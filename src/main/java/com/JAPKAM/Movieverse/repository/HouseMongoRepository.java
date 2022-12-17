package com.JAPKAM.Movieverse.repository;

import com.JAPKAM.Movieverse.entity.House;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseMongoRepository extends MongoRepository<House, String> {
}
