package com.rest.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rest.api.models.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
