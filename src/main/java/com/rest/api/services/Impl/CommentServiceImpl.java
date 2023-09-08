package com.rest.api.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.exceptions.ResouceNotFound;
import com.rest.api.models.Comment;
import com.rest.api.models.Post;
import com.rest.api.payloads.CommentDto;
import com.rest.api.repositories.CommentRepo;
import com.rest.api.repositories.PostRepo;
import com.rest.api.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper mm;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResouceNotFound("Post","postId",postId));
		Comment comment = this.mm.map(commentDto,Comment.class);
		comment.setPost(post);
		Comment savedCommnet = this.commentRepo.save(comment);
		
		return this.mm.map(savedCommnet, CommentDto.class);
	}

	@Override
	public void delete(Integer commentId) {
		
		Comment com = this.commentRepo.findById(commentId).orElseThrow(()->new ResouceNotFound("Comment","commnetId",commentId));
		this.commentRepo.delete(com);
	}

}
