package com.ren.api.service;

import com.ren.api.dto.TagDto;
import com.ren.api.mapper.ObjectMapper;
import com.ren.api.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aneagu on 13/01/2020.
 */
@Service
public class TagServiceImpl implements TagService {

    public final ObjectMapper objectMapper;

    public final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(ObjectMapper objectMapper, TagRepository tagRepository) {
        this.objectMapper = objectMapper;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<TagDto> findAllTags() {
        List<TagDto> result = new ArrayList<>();
        tagRepository.findAll().forEach(tag -> result.add(objectMapper.convertTagToTagDto(tag)));
        return result;
    }

    @Override
    public List<TagDto> findAllByIds(List<Long> ids) {
        List<TagDto> tags = new ArrayList<>();
        tagRepository.findAllByIds(ids)
                .forEach(tag -> tags.add(objectMapper.convertTagToTagDto(tag)));

        return tags;
    }
}
