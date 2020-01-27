package com.ren.api.controller;

import com.ren.api.dto.QuestionDto;
import com.ren.api.exceptions.RenException;
import com.ren.api.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<?> postQuestion(@RequestBody @Valid QuestionDto questionDto) {
        questionService.save(questionDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<QuestionDto>> getQuestions(@RequestParam(name = "page", defaultValue = "0") int page,
                                                          @RequestParam(name = "size", defaultValue = "10") int size,
                                                          @RequestParam(name = "sort", defaultValue = "DESC") String sort,
                                                          @RequestParam(name = "sortedParam", defaultValue = "createdOn") String sortedParam,
                                                          @RequestParam(name = "searchKey", defaultValue = "") String searchKey) {
        Page<QuestionDto> questionDtoPage = questionService.getQuestions(searchKey, PageRequest.of(page, size, Sort.Direction.valueOf(sort), sortedParam));
        return ResponseEntity.ok(questionDtoPage);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable("id") Long id, @RequestBody @NotNull @Valid QuestionDto questionDto) throws RenException {
        questionService.update(id, questionDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable("id") Long id) throws RenException {
        QuestionDto questionDto = questionService.getQuestionById(id);
        return ResponseEntity.ok(questionDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") Long id) throws RenException {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}