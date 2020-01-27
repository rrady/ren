package com.ren.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ren.api.domain.Comment;
import com.ren.api.dto.CommentDto;
import com.ren.api.exceptions.Codes;
import com.ren.api.exceptions.RenException;
import com.ren.api.mapper.ObjectMapper;
import com.ren.api.repository.CommentRepository;

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
    public void update(Long id, CommentDto commentDto) throws RenException {
        Optional<Comment> optional = commentRepository.findById(id);
        if (optional.isPresent()) {
            Comment comment = objectMapper.convertCommentDtoToComment(commentDto);
            comment.setId(id);
            commentRepository.save(comment);
        } else {
            throw new RenException(Codes.RESOURCE_NOT_FOUND, String.format("Comment with id: '%s' was not found!", id));
        }
    }

    @Override
    public List<CommentDto> findAllByAnswerId(Long answerId) {
        List<CommentDto> resultList = new ArrayList<>();
        commentRepository.findAllByAnswerId(answerId)
                .forEach(comment -> resultList.add(objectMapper.convertCommentToCommentDto(comment)));
        return resultList;
    }

    @Override
    public void delete(Long id) throws RenException {
        Optional<Comment> optional = commentRepository.findById(id);
        if (optional.isPresent()) {
            commentRepository.deleteById(id);
        } else {
            throw new RenException(Codes.RESOURCE_NOT_FOUND, String.format("Comment with id: '%s' was not found!", id));
        }
    }
}
