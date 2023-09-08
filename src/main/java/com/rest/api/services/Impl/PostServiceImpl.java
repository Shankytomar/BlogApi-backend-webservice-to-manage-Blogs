package com.rest.api.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rest.api.exceptions.ResouceNotFound;
import com.rest.api.models.Category;
import com.rest.api.models.Post;
import com.rest.api.models.User;
import com.rest.api.payloads.PostDto;
import com.rest.api.payloads.PostResponse;
import com.rest.api.repositories.CategoryRepo;
import com.rest.api.repositories.PostRepo;
import com.rest.api.repositories.UserRepo;
import com.rest.api.services.PostService;

@Service
public class PostServiceImpl implements PostService  {

	@Autowired
	private PostRepo postrepo;
	@Autowired
	private ModelMapper mm;
	@Autowired
	private UserRepo userrepo;
	@Autowired
	private CategoryRepo categoryrepo;
	@Override
	public PostDto createPost(PostDto pdto,Integer userId,Integer categoryId) {
		
		User u =this.userrepo.findById(userId).orElseThrow(()->new ResouceNotFound("user","id",userId));
		Category c = this.categoryrepo.findById(categoryId).orElseThrow(()->new ResouceNotFound("category","id",categoryId));
		// TODO Auto-generated method stub
		Post p = this.mm.map(pdto,Post.class);
		p.setAddeddate(new Date());
		p.setUser(u);
		p.setCategory(c);
		
		Post ps = this.postrepo.save(p);
		
		return this.mm.map(ps, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto pdto,Integer id) {
		Post p = this.postrepo.findById(id).orElseThrow(()->new ResouceNotFound("post","id",id));
		p.setTitle(pdto.getTitle());
		p.setContent(pdto.getContent());
		Post ps = this.postrepo.save(p);

		return this.mm.map(ps,PostDto.class);
	}

	@Override
	public void deletePost(Integer pid) {
		Post p = this.postrepo.findById(pid).orElseThrow(()->new ResouceNotFound("post","id",pid));
		this.postrepo.delete(p);
	}

	@Override
	public PostDto getPost(Integer pid) {
		// TODO Auto-generated method stub
		Post p = this.postrepo.findById(pid).orElseThrow(()->new ResouceNotFound("post","id",pid));
		return this.mm.map(p,PostDto.class);
	}

	
	// adding pagination
	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost = this.postrepo.findAll(p);
		List<Post> pageList = pagePost.getContent();
		List<PostDto> pd = pageList.stream().map((post)->this.mm.map(post, PostDto.class)).collect(Collectors.toList());
		//
		PostResponse postResponse = new PostResponse();
		postResponse.setPostDtoList(pd);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getSize());
		postResponse.setLastPage(false);
		return postResponse;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User u = this.userrepo.findById(userId).orElseThrow(()->new ResouceNotFound("user","id",userId));
		List<Post> posts = this.postrepo.findByUser(u);
		List<PostDto> postDtos = posts.stream().map((post)->this.mm.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostbyCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category c =this.categoryrepo.findById(categoryId).orElseThrow(()-> new ResouceNotFound("category","id",categoryId));
		List<Post> posts = this.postrepo.findByCategory(c);
		List<PostDto> postDtos = posts.stream().map((post)-> this.mm.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}




















