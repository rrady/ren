package io.ren.api.controller;

import io.ren.api.aop.EnteringExitingLog;
import io.ren.api.dto.QuestionDto;
import io.ren.api.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by aneagu on 08/01/2020.
 */
@RestController("/questions")
public class QuestionsController {

    private final QuestionService questionService;

    @Autowired
    public QuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @EnteringExitingLog
    @PostMapping("/add")
    public void postQuestion(@RequestBody QuestionDto questionDto) {
        questionService.createQuestion(questionDto);
    }

    @EnteringExitingLog
    @GetMapping("/all")
    public ResponseEntity<Page<QuestionDto>> getQuestions(@RequestParam(name = "page", defaultValue = "0") int page,
                                                          @RequestParam(name = "size", defaultValue = "15") int size,
                                                          @RequestParam(name = "sort", defaultValue = "DESC") String sort) {
        Page<QuestionDto> questionDtoPage = questionService.getQuestions(PageRequest.of(page, size, Sort.Direction.valueOf(sort)));
        return ResponseEntity.ok(questionDtoPage);
    }
}
