package com.JAPKAM.Movieverse.controller.mapper;

import com.JAPKAM.Movieverse.controller.dto.CommentCreateRequest;
import com.JAPKAM.Movieverse.entity.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toEntity(CommentCreateRequest commentCreateRequest) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentCreateRequest, comment);;
        return comment;
    }
}
