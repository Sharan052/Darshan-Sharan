package com.example.service;

import java.util.List;

import com.example.dto.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);

	List<PostDto> getAllPosts();

	void deletePostAndComments(Long postId);
	
	 
}

