package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.controller.dto.CommentCreateRequest;
import com.JAPKAM.Movieverse.entity.Comment;
import com.JAPKAM.Movieverse.repository.CommentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTests {

    @Autowired
    MockMvc client;

    @Autowired
    CommentRepository commentRepository;

    @BeforeEach
    public void clearDB(){
        commentRepository.deleteAll();
    }

    public static final String MOVIE_ID_1 = new ObjectId().toString();
    public static final String ICON_LINK_1 = "ICON_LINK_1";
    public static final String CONTENT_1 = "CONTENT_1";
    public static final String MOVIE_ID_2 = new ObjectId().toString();
    public static final String ICON_LINK_2 = "ICON_LINK_2";
    public static final String CONTENT_2 = "CONTENT_2";
    public static final String ICON_LINK_3 = "ICON_LINK_3";
    public static final String CONTENT_3 = "CONTENT_3";


    @Test
    void should_get_comment_of_movie_1_when_find_by_movie_id_given_movie_id() throws Exception {
        //given
        Comment comment1 = new Comment(MOVIE_ID_1, ICON_LINK_1, CONTENT_1);
        Comment comment2 = new Comment(MOVIE_ID_1, ICON_LINK_2, CONTENT_2);
        Comment comment3 = new Comment(MOVIE_ID_2, ICON_LINK_3, CONTENT_3);
        commentRepository.saveAll(Arrays.asList(comment1,comment2, comment3));
        //when
        //then
        client.perform(MockMvcRequestBuilders.get("/comments?movieId={movieId}", MOVIE_ID_1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath(("$[0].id")).isString())
                .andExpect(MockMvcResultMatchers.jsonPath(("$[1].id")).isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].movieId", containsInAnyOrder(MOVIE_ID_1, MOVIE_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].iconLink", containsInAnyOrder(ICON_LINK_1, ICON_LINK_2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].content" , containsInAnyOrder(CONTENT_1, CONTENT_2)));
    }

    @Test
    void should_return_created_comment_when_create_comment_given_create_comment_request() throws Exception{
        //given
        CommentCreateRequest commentCreateRequest = new CommentCreateRequest(MOVIE_ID_1, ICON_LINK_1, CONTENT_1);
        String commentCreateRequestString = new ObjectMapper().writeValueAsString(commentCreateRequest);
        //when
        //then
        client.perform(MockMvcRequestBuilders.post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(commentCreateRequestString)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath(("$.id")).isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.movieId").value(MOVIE_ID_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.iconLink").value(ICON_LINK_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(CONTENT_1));
    }

}
