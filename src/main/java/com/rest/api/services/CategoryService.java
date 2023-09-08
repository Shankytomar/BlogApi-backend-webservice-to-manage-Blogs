package com.rest.api.services;

import java.util.List;

import com.rest.api.payloads.CategoryDto;

public interface CategoryService {
	
	public CategoryDto createCategory(CategoryDto cdto);
	public CategoryDto updateCategory(CategoryDto cdto, Integer cid);
	public void deleteCategory(Integer cid);
	public CategoryDto getCategory(Integer id);
	public List<CategoryDto> getAllCartegory();
}
