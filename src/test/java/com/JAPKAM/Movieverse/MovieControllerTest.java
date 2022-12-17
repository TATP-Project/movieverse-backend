package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.Movie;
import com.JAPKAM.Movieverse.entity.Tag;
import com.JAPKAM.Movieverse.repository.MovieRepository;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    MockMvc client;

    @Autowired
    MovieRepository movieRepository;

    @BeforeEach
    public void clearDB() {
        movieRepository.deleteAll();
    }

    @Test
    void should_get_all_movies_when_perform_get_given_2_movies() throws Exception {
        //given
        List<Tag> tags = new ArrayList<>();
        Tag tag1 = new Tag(new ObjectId().toString(),"action");
        Tag tag2 = new Tag(new ObjectId().toString(),"romantic");
        tags.add(tag1);
        tags.add(tag2);
        Binary image1 = new Binary(new byte[1]);
        Binary image2 = new Binary(new byte[1]);
        Movie movie1 = new Movie(new ObjectId().toString(),"Movie 1", tags,image1);
        Movie movie2 = new Movie(new ObjectId().toString(),"Movie 2", tags,image2);
        movieRepository.save(movie1);
        movieRepository.save(movie2);
        //when & then

        client.perform(MockMvcRequestBuilders.get("/movies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(2)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Movie 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Movie 2"));
    }

    @Test
    void should_get_movie_when_perform_get_by_id_given_a_id() throws Exception {
        //given
        List<Tag> tags = new ArrayList<>();
        Tag tag = new Tag(new ObjectId().toString(),"action");
        tags.add(tag);
        Binary image = new Binary(new byte[1]);
        String id = new ObjectId().toString();
        Movie movie = new Movie(id,"Movie 1", tags,image);
        movieRepository.save(movie);
        //when & then
        client.perform(MockMvcRequestBuilders.get("/movies/{id}",movie.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Movie 1"));
    }
}
