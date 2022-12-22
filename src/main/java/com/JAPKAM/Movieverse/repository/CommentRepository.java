package com.JAPKAM.Movieverse.repository;

import com.JAPKAM.Movieverse.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByMovieId(String movieId);
}
