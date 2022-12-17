package com.JAPKAM.Movieverse.service;

import com.JAPKAM.Movieverse.entity.Tag;
import com.JAPKAM.Movieverse.repository.TagMongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    TagMongoRepository tagMongoRepository;

    public TagService(TagMongoRepository tagMongoRepository) {
        this.tagMongoRepository = tagMongoRepository;
    }

    public List<Tag> findAll() {
        return tagMongoRepository.findAll();
    }
}
