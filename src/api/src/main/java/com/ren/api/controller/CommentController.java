package com.ren.api.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ren.api.dto.CommentDto;
import com.ren.api.exceptions.RenException;
import com.ren.api.service.CommentService;

@RestController
@RequestMapping(value = "/api/question/{questionId}/answer/{answerId}/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<?> postAnswer(@RequestBody @NotNull @Valid CommentDto commentDto) {
        commentService.save(commentDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAnswersForQuestion(@PathVariable(value = "answerId") Long answerId) {
        List<CommentDto> resultList = commentService.findAllByAnswerId(answerId);
        return ResponseEntity.ok(resultList);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateAnswer(@PathVariable("id") Long id, @RequestBody @NotNull @Valid CommentDto commentDto) throws RenException {
        commentService.update(id, commentDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("id") Long id) throws RenException {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
