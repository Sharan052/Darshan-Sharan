package com.example.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.CommentRepository;
import com.example.Repository.PostRepository;
import com.example.dto.CommentDto;
import com.example.entity.Comment;
import com.example.entity.Post;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
    private CommentRepository commentRepository;
	@Autowired
    private PostRepository postRepository;
	@Autowired
    private ModelMapper mapper;
    
   

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);

        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow();

        // set post to comment entity
        comment.setPost(post);

        // comment entity to DB
        Comment newComment =  commentRepository.save(comment);

        return mapToDTO(newComment);
    }

private CommentDto mapToDTO(Comment comment){
       // CommentDto commentDto = mapper.map(comment, CommentDto.class);

        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return  commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
       // Comment comment = mapper.map(commentDto, Comment.class);
       Comment comment = new Comment();
       comment.setId(commentDto.getId());
       comment.setName(commentDto.getName());
       comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return  comment;
    }

	

}

