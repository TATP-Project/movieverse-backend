package com.JAPKAM.Movieverse.controller;

import com.JAPKAM.Movieverse.entity.Comment;
import com.JAPKAM.Movieverse.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(params = "movieId")
    public List<Comment> getCommentsByMovieId(@RequestParam String movieId) {
        return commentService.findByMovieId(movieId);
    }
}
