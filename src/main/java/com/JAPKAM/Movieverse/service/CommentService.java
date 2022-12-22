package com.JAPKAM.Movieverse.service;

import com.JAPKAM.Movieverse.entity.Comment;
import com.JAPKAM.Movieverse.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findByMovieId(String movieId) {
        return commentRepository.findByMovieId(movieId);
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
