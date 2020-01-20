package com.ren.api.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ren.api.dto.QuestionDto;
import com.ren.api.exceptions.RenException;
import com.ren.api.service.QuestionService;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping()
    public ResponseEntity<?> postQuestion(@RequestBody @Valid QuestionDto questionDto) {
        questionService.save(questionDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<Page<QuestionDto>> getQuestions(@RequestParam(name = "page", defaultValue = "0") int page,
                                                          @RequestParam(name = "size", defaultValue = "10") int size,
                                                          @RequestParam(name = "sort", defaultValue = "DESC") String sort,
                                                          @RequestParam(name = "sortedParam", defaultValue = "createdOn") String sortedParam) {
        Page<QuestionDto> questionDtoPage = questionService.getQuestionsPaginated(PageRequest.of(page, size, Sort.Direction.valueOf(sort), sortedParam));
        return ResponseEntity.ok(questionDtoPage);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable("id") Long id, @RequestBody @NotNull @Valid QuestionDto questionDto) throws RenException {
        questionService.update(id, questionDto);
        return ResponseEntity.noContent().build();
    }
}