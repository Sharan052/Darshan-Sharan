package com.example.service;

import com.example.dto.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
}
