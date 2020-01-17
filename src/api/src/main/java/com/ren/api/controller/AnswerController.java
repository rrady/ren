package com.ren.api.controller;

import com.ren.api.dto.AnswerDto;
import com.ren.api.dto.QuestionDto;
import com.ren.api.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by aneagu on 14/01/2020.
 */
@RestController
@RequestMapping(value = "/api/answers/")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> postAnswer(@RequestBody @NotNull @Valid AnswerDto answerDto) {
        answerService.save(answerDto);
        return ResponseEntity.ok("Answer successfully inserted!");
    }

    @GetMapping(value = "/question/{questionId}")
    public ResponseEntity<List<AnswerDto>> getAnswersForQuestion(@PathParam(value = "questionId") Long questionId) {
        List<AnswerDto> resultList = answerService.findAllByQuestionId(questionId);
        return ResponseEntity.ok(resultList);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateAnswer(@PathParam("id") Long id, @RequestBody @NotNull @Valid AnswerDto answerDto) {
        answerService.update(id, answerDto);
        return ResponseEntity.status(HttpStatus.OK).body("Answer successfully updated!");
    }
}
