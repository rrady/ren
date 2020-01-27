package com.ren.api.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ren.api.dto.AnswerDto;
import com.ren.api.exceptions.RenException;
import com.ren.api.service.AnswerService;

@RestController
@RequestMapping(value = "/api/question/{questionId}/answer")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<?> postAnswer(@PathVariable(value = "questionId") Long questionId,
        @RequestBody @NotNull @Valid AnswerDto answerDto) {

        if (answerDto.getQuestionId() == null) {
            answerDto.setQuestionId(questionId);
        }

        answerService.save(answerDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AnswerDto>> getAnswersForQuestion(@PathVariable(value = "questionId") Long questionId) {
        List<AnswerDto> resultList = answerService.findAllByQuestionId(questionId);
        return ResponseEntity.ok(resultList);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateAnswer(@PathVariable("id") Long id, @RequestBody @NotNull @Valid AnswerDto answerDto) throws RenException {
        answerService.update(id, answerDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("id") Long id) throws RenException {
        answerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
