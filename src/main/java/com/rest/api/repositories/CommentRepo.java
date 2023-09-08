package com.rest.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.models.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
