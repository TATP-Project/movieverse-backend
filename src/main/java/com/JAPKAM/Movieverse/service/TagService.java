package com.JAPKAM.Movieverse.service;

import com.JAPKAM.Movieverse.entity.Tag;
import com.JAPKAM.Movieverse.exception.TagNotFoundException;
import com.JAPKAM.Movieverse.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Tag findById(String id) {
        return tagRepository.findById(id).orElseThrow(TagNotFoundException::new);
    }
}
