package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PostDto;
import com.example.service.PostService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
     return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    
    
    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }
    
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePostAndComments(@PathVariable Long postId) {
        try {
            postService.deletePostAndComments(postId);
            return new ResponseEntity<>("Post with Comments are deleted successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
