package com.example.service;

import java.util.List;

import com.example.dto.CommentDto;

public interface CommentService {
	
    CommentDto createComment(long postId, CommentDto commentDto);
    
    List<CommentDto> getCommentsByPostId(long postId);
    
    CommentDto getCommentById(Long postId, Long commentId) throws Exception;
    

    CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest);

    void deleteComment(Long postId, Long commentId);
}
