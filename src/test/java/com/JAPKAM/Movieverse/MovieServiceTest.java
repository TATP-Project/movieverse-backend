package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.Movie;
import com.JAPKAM.Movieverse.entity.Tag;
import com.JAPKAM.Movieverse.repository.MovieRepository;
import com.JAPKAM.Movieverse.service.MovieService;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class MovieServiceTest {
    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieService movieService;

    @Test
    void should_return_all_movie_when_find_all_given_movies() throws IOException {
        //given
        List<Movie> movies = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        Tag tag1 = new Tag(new ObjectId().toString(),"action");
        Tag tag2 = new Tag(new ObjectId().toString(),"romantic");
        tags.add(tag1);
        tags.add(tag2);
        Binary image1 = new Binary(new byte[1]);
        Binary image2 = new Binary(new byte[1]);
        Movie movie1 = new Movie(new ObjectId().toString(),"Movie 1", tags,image1);
        Movie movie2 = new Movie(new ObjectId().toString(),"Movie 2", tags,image2);
        movies.add(movie1);
        movies.add(movie2);
        when(movieRepository.findAll()).thenReturn(movies);
        //when
        List<Movie> result = movieService.findAll();
        //then
        assertThat(result,hasSize(2));
        assertThat(result.get(0), equalTo(movie1));
        assertThat(result.get(1), equalTo(movie2));
        verify(movieRepository).findAll();
    }

    @Test
    void should_return_movie_when_find_by_id_given_movie() {
        //given
        List<Tag> tags = new ArrayList<>();
        Tag tag = new Tag(new ObjectId().toString(),"action");
        tags.add(tag);
        Binary image = new Binary(new byte[1]);
        String id = new ObjectId().toString();
        Movie movie = new Movie(id,"Movie 1", tags,image);
        given(movieRepository.findById(id)).willReturn(Optional.of(movie));
        //when
        Movie result = movieService.findById(id);
        //then
        verify(movieRepository).findById(id);
        assertThat(result,equalTo(movie));
    }
}
