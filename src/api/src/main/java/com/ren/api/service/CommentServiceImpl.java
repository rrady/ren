package com.ren.api.service;


import com.ren.api.domain.Comment;
import com.ren.api.dto.AnswerDto;
import com.ren.api.dto.CommentDto;
import com.ren.api.exceptions.NotFoundException;
import com.ren.api.mapper.ObjectMapper;
import com.ren.api.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by aneagu on 14/01/2020.
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final ObjectMapper objectMapper;

    public CommentServiceImpl(CommentRepository commentRepository, ObjectMapper objectMapper) {
        this.commentRepository = commentRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void save(CommentDto commentDto) {
        commentRepository.save(objectMapper.convertCommentDtoToComment(commentDto));
    }

    @Override
    public void update(Long id, CommentDto commentDto) {
        Optional<Comment> optional = commentRepository.findById(id);
        if (optional.isPresent()) {
            Comment answer = objectMapper.convertCommentDtoToComment(commentDto);
            commentRepository.save(answer);
        } else {
            throw new NotFoundException("Entity: " + Comment.class + " not found!");
        }
    }

    @Override
    public List<CommentDto> findAllByAnswerId(Long answerId) {
        List<CommentDto> resultList = new ArrayList<>();
        commentRepository.findAllByAnswerId(answerId)
                .forEach(comment -> resultList.add(objectMapper.convertCommentToCommentDto(comment)));
        return resultList;
    }
}
