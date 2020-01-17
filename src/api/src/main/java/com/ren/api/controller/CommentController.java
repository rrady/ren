package com.ren.api.controller;

import com.ren.api.dto.AnswerDto;
import com.ren.api.dto.CommentDto;
import com.ren.api.service.CommentService;
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
@RequestMapping(value = "/api/comments/")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> postAnswer(@RequestBody @NotNull @Valid CommentDto commentDto) {
        commentService.save(commentDto);
        return ResponseEntity.ok("Answer successfully inserted!");
    }

    @GetMapping(value = "/question/{answerId}")
    public ResponseEntity<List<CommentDto>> getAnswersForQuestion(@PathParam(value = "answerId") Long answerId) {
        List<CommentDto> resultList = commentService.findAllByAnswerId(answerId);
        return ResponseEntity.ok(resultList);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateAnswer(@PathParam("id") Long id, @RequestBody @NotNull @Valid CommentDto commentDto) {
        commentService.update(id, commentDto);
        return ResponseEntity.status(HttpStatus.OK).body("Answer successfully updated!");
    }
}
