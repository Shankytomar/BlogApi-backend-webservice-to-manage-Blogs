package com.rest.api.services;

import java.util.List;
import com.rest.api.payloads.PostDto;
import com.rest.api.payloads.PostResponse;


public interface PostService {

	//create
	public PostDto createPost(PostDto pdto,Integer userId,Integer categoryId);
	//update
	public PostDto updatePost(PostDto pdto,Integer id);
	//delete
	public void deletePost(Integer pid);
	//get
	public PostDto getPost(Integer pid);
	//get all
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize );
	//all post by user
	public List<PostDto> getPostByUser(Integer userId);
	//all post by category
	public List<PostDto> getPostbyCategory(Integer categoryId);
}
