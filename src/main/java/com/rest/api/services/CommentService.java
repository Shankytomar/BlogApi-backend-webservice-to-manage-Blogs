package com.rest.api.services;

import com.rest.api.payloads.CommentDto;

public interface CommentService {

	public CommentDto createComment(CommentDto commentDto,Integer postId);
	public void delete(Integer commentId);
}
