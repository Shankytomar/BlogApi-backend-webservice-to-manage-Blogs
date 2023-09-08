package com.rest.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.models.Category;
import com.rest.api.models.Post;
import com.rest.api.models.User;

public interface PostRepo extends JpaRepository<Post,Integer> {
	List<Post> findByCategory(Category c);
	List<Post> findByUser(User u);

	
}
