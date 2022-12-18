package com.JAPKAM.Movieverse.repository;

import com.JAPKAM.Movieverse.entity.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends MongoRepository<Tag, String> {
}
