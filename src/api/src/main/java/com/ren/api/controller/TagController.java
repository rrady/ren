package com.ren.api.controller;

import com.ren.api.dto.TagDto;
import com.ren.api.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aneagu on 14/01/2020.
 */
@RestController
@RequestMapping(value = "/api/tags/")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveTag(@RequestBody TagDto tagDto) {
        tagService.save(tagDto);
        return ResponseEntity.ok("Tag inserted!");
    }
}
