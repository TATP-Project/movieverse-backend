package com.JAPKAM.Movieverse.repository;

import com.JAPKAM.Movieverse.entity.House;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends MongoRepository<House, String> {
}
