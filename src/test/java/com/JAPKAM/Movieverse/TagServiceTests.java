package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.Tag;
import com.JAPKAM.Movieverse.repository.TagMongoRepository;
import com.JAPKAM.Movieverse.service.TagService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
public class TagServiceTests {

    @Autowired
    TagMongoRepository tagMongoRepository;

    @Autowired
    TagService tagService;

    @BeforeEach
    public void clearDB(){
        tagMongoRepository.deleteAll();
    }

    public static final String SUPERHERO_TAG = "superhero";
    public static final String MARVEL_TAG = "marvel";
    public static final String BAT_TAG = "bat";

    @Test
    void should_return_all_tags_when_find_all_given_tags() {
        //given
        Tag tag1 = new Tag(new ObjectId().toString(), SUPERHERO_TAG);
        Tag tag2 = new Tag(new ObjectId().toString(), MARVEL_TAG);
        Tag tag3 = new Tag(new ObjectId().toString(), MARVEL_TAG);
        tagMongoRepository.saveAll(Arrays.asList(tag1, tag2, tag3));

        //when
        List<Tag> tags = tagService.findAll();

        //then

        assertThat(tags, hasSize(3));
        assertThat(tags.get(0).getId(), equalTo(tag1.getId()));
        assertThat(tags.get(0).getName(), equalTo(tag1.getName()));
        assertThat(tags.get(1).getId(), equalTo(tag2.getId()));
        assertThat(tags.get(1).getName(), equalTo(tag2.getName()));
        assertThat(tags.get(2).getId(), equalTo(tag3.getId()));
        assertThat(tags.get(2).getName(), equalTo(tag3.getName()));
    }

    @Test
    void should_return_tag_3_when_find_by_id_given_id() {
        //given
        Tag tag1 = new Tag(new ObjectId().toString(), SUPERHERO_TAG);
        Tag tag2 = new Tag(new ObjectId().toString(), MARVEL_TAG);
        Tag tag3 = new Tag(new ObjectId().toString(), MARVEL_TAG);
        tagMongoRepository.saveAll(Arrays.asList(tag1, tag2, tag3));

        //when
        Tag returnedTag = tagService.findById(tag3.getId());

        //then

        assertThat(returnedTag.getId(), equalTo(tag3.getId()));
        assertThat(returnedTag.getName(), equalTo(tag3.getName()));
    }
}
