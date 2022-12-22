package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.Comment;
import com.JAPKAM.Movieverse.repository.CommentRepository;
import com.JAPKAM.Movieverse.service.CommentService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CommentServiceTests {

    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    CommentService commentService;

    public static final String MOVIE_ID_1 = new ObjectId().toString();
    public static final String ICON_LINK_1 = "ICON_LINK_1";
    public static final String CONTENT_1 = "CONTENT_1";
    public static final String MOVIE_ID_2 = new ObjectId().toString();
    public static final String ICON_LINK_2 = "ICON_LINK_2";
    public static final String CONTENT_2 = "CONTENT_2";
    public static final String ICON_LINK_3 = "ICON_LINK_3";
    public static final String CONTENT_3 = "CONTENT_3";

    @Test
    void should_return_comments_of_the_movie__when_find_by_movieId_given_comments_and_movie_id() {
        //given
        String movieId = MOVIE_ID_1;
        Comment comment1 = new Comment(MOVIE_ID_1, ICON_LINK_1, CONTENT_1);
        Comment comment2 = new Comment(MOVIE_ID_1, ICON_LINK_2, CONTENT_2);
        when(commentRepository.findByMovieId(movieId)).thenReturn(Arrays.asList(comment1, comment2));

        //when
        List<Comment> comments = commentService.findByMovieId(movieId);

        //then
        assertThat(comments, hasSize(2));
        assertThat(comments.get(0), equalTo(comment1));
        assertThat(comments.get(1), equalTo(comment2));
        verify(commentRepository).findByMovieId(movieId);
    }
}
