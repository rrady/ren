package com.ren.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ren.api.dto.TagDto;
import com.ren.api.mapper.ObjectMapper;
import com.ren.api.repository.TagRepository;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, ObjectMapper objectMapper) {
        this.tagRepository = tagRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<TagDto> getAll() {
        List<TagDto> resultList = new ArrayList<>();
        tagRepository.findAll()
            .forEach(tag -> resultList.add(objectMapper.convertTagToTagDto(tag)));
            
        return resultList;
    }
}
