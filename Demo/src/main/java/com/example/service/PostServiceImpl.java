package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.CommentRepository;
import com.example.Repository.PostRepository;
import com.example.dto.PostDto;
import com.example.entity.Post;

import jakarta.persistence.EntityNotFoundException;


@Service
public class PostServiceImpl  implements PostService {

	@Autowired
    private PostRepository postRepository;
    
	@Autowired 
   private ModelMapper mapper;

	@Autowired
   private CommentRepository commentRepository; 

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert DTO to entity
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);

        // convert entity to DTO
        PostDto postResponse = mapToDTO(newPost);
        return postResponse;
    }
    
    
    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }
    
	@Override
	public void deletePostAndComments(Long postId) {
	       Optional<Post> postOptional = postRepository.findById(postId);
	       if(postOptional.isPresent())
	       {
	    	   Post post = postOptional.get();
	           commentRepository.deleteAll(post.getComments());
	           postRepository.delete(post);
	       }
	       else {
	    	   throw new EntityNotFoundException("Post with ID " + postId + " not found");
	       }
	       
	}


// convert Entity into DTO
    private PostDto mapToDTO(Post post){
       
        
        PostDto postDto = mapper.map(post, PostDto.class);
    	
    	/* PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());*/
        return postDto;  
    }

    // convert DTO to entity
    private Post mapToEntity(PostDto postDto){
      
    	Post post = mapper.map(postDto, Post.class);
    	
       /* Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());*/
        return post;
    }



}
