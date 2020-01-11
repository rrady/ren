package com.ren.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ren.api.domain.exceptions.RenException;
import com.ren.api.dto.QuestionDto;
import com.ren.api.service.QuestionService;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    
    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping()
    public ResponseEntity<?> create() throws RenException {
        return ResponseEntity.ok().body(questionService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody QuestionDto questionDto) throws RenException {
        questionService.createQuestion(questionDto);

        return ResponseEntity.noContent().build();
    }
}