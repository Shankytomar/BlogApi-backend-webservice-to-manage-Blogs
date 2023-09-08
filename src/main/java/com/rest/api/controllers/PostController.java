package com.rest.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.payloads.ApiResponse;
import com.rest.api.payloads.PostDto;
import com.rest.api.payloads.PostResponse;
import com.rest.api.services.PostService;

@RestController
@RequestMapping("api")
public class PostController {
	
	@Autowired
	private PostService postservice;

	//create
	@PostMapping("/user/{userid}/category/{categoryid}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postdto,
			@PathVariable Integer userid,
			@PathVariable Integer categoryid
			){
		
		PostDto ps =this.postservice.createPost(postdto, userid, categoryid);
		return new ResponseEntity<PostDto>(ps,HttpStatus.CREATED);	
	}
	// get all posts by user
	@GetMapping("/user/{userid}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userid){
		List<PostDto> pdtos =this.postservice.getPostByUser(userid);
		return new ResponseEntity<List<PostDto>>(pdtos,HttpStatus.OK);
		
	}   
	
	// get all post by category
	@GetMapping("/category/{categoryid}/posts")
	public ResponseEntity<List<PostDto>> getAllpostByCategory(@PathVariable Integer categoryid){
		List<PostDto> pdtos = this.postservice.getPostbyCategory(categoryid);
		return new ResponseEntity<List<PostDto>>(pdtos,HttpStatus.OK);
	}
	
	//get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(@RequestParam(value="pageNumber",defaultValue="0", required=false) Integer pageNumber,@RequestParam(value="pageSize",defaultValue="4", required=false) Integer pageSize){
		PostResponse pdto = this.postservice.getAllPost(pageNumber,pageSize);
		return new ResponseEntity<PostResponse>(pdto,HttpStatus.ACCEPTED);
	}
	
	//get post by post id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		return new ResponseEntity<PostDto>(this.postservice.getPost(postId),HttpStatus.OK);
	}
	
	//delete post
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postservice.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted successfully",true), HttpStatus.OK);
	}
	//update post
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto pdto,@PathVariable Integer postId){
		PostDto ps = this.postservice.updatePost(pdto, postId);
		return new ResponseEntity<PostDto>(ps,HttpStatus.OK);
	}
	
}