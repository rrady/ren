package com.ren.api.controller;

import com.ren.api.dto.QuestionDto;
import com.ren.api.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseEntity<String> postQuestion(@RequestBody @NotNull @Valid QuestionDto questionDto, @NotNull @Size(min = 1) List<Long> tags) {
        questionService.createQuestion(questionDto, tags);

        return ResponseEntity.status(HttpStatus.OK).body("Question successfully inserted!");
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Page<QuestionDto>> getQuestions(@RequestParam(name = "page", defaultValue = "0") int page,
                                                          @RequestParam(name = "size", defaultValue = "15") int size,
                                                          @RequestParam(name = "sort", defaultValue = "DESC") String sort,
                                                          @RequestParam(name = "sortedParam", defaultValue = "createdOn") String sortedParam) {
        Page<QuestionDto> questionDtoPage = questionService.getQuestionsPaginated(PageRequest.of(page, size, Sort.Direction.valueOf(sort), sortedParam));

        return ResponseEntity.ok(questionDtoPage);
    }
}