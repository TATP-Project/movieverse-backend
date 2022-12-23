package com.JAPKAM.Movieverse.controller;

import com.JAPKAM.Movieverse.controller.dto.CommentCreateRequest;
import com.JAPKAM.Movieverse.controller.mapper.CommentMapper;
import com.JAPKAM.Movieverse.entity.Comment;
import com.JAPKAM.Movieverse.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;
    private CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @GetMapping(params = "movieId")
    public List<Comment> getCommentsByMovieId(@RequestParam String movieId) {
        return commentService.findByMovieId(movieId);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Comment postComment(@RequestBody CommentCreateRequest commentCreateRequest){
        Comment comment = commentMapper.toEntity(commentCreateRequest);
        return commentService.createComment(comment);
    }
}
