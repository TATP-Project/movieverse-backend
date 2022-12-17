package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.Tag;
import com.JAPKAM.Movieverse.repository.TagRepository;
import com.JAPKAM.Movieverse.service.TagService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TagServiceTests {

    @Mock
    TagRepository tagRepository;

    @InjectMocks
    TagService tagService;


    public static final String SUPERHERO_TAG = "superhero";
    public static final String MARVEL_TAG = "marvel";

    @Test
    void should_return_all_tags_when_find_all_given_tags() {
        //given
        Tag tag1 = new Tag(new ObjectId().toString(), SUPERHERO_TAG);
        Tag tag2 = new Tag(new ObjectId().toString(), MARVEL_TAG);
        Tag tag3 = new Tag(new ObjectId().toString(), MARVEL_TAG);
        List<Tag> tags = Arrays.asList(tag1, tag2, tag3);

        when(tagRepository.findAll()).thenReturn(tags);

        //when
        List<Tag> returnedTags = tagService.findAll();

        //then

        assertThat(returnedTags, hasSize(3));
        assertThat(returnedTags.get(0), equalTo(tag1));
        assertThat(returnedTags.get(1), equalTo(tag2));
        assertThat(returnedTags.get(2), equalTo(tag3));
        verify(tagRepository).findAll();
    }

    @Test
    void should_return_tag_3_when_find_by_id_given_id() throws Exception {
        //given
        Tag tag1 = new Tag(new ObjectId().toString(), SUPERHERO_TAG);
        Tag tag2 = new Tag(new ObjectId().toString(), MARVEL_TAG);
        Tag tag3 = new Tag(new ObjectId().toString(), MARVEL_TAG);
        List<Tag> tags = Arrays.asList(tag1, tag2, tag3);

        when(tagRepository.findById(tag3.getId())).thenReturn(Optional.of(tag3));
        //when
        Tag returnedTag = tagService.findById(tag3.getId());

        //then

        assertThat(returnedTag, equalTo(tag3));
        verify(tagRepository).findById(tag3.getId());
    }
}
