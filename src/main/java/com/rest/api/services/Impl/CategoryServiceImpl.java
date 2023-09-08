package com.rest.api.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.exceptions.ResouceNotFound;
import com.rest.api.models.Category;
import com.rest.api.payloads.CategoryDto;
import com.rest.api.repositories.CategoryRepo;
import com.rest.api.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	public CategoryRepo categoryrepo;
	
	@Autowired
	public ModelMapper mm;

	@Override
	public CategoryDto createCategory(CategoryDto cdto) {
		// TODO Auto-generated method stub
		Category c = this.mm.map(cdto, Category.class);
		Category cs = this.categoryrepo.save(c);
		return this.mm.map(cs, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto cdto, Integer cid) {
		// TODO Auto-generated method stub
		Category c = this.categoryrepo.findById(cid).orElseThrow(()->new ResouceNotFound("category","id",cid));
		c.setCategoryDescription(cdto.getCategoryDescription());
		c.setCategoryTitle(cdto.getCategoryTitle());
		Category cu =this.categoryrepo.save(c);
		return this.mm.map(cu, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer cid) {
		// TODO Auto-generated method stub
		Category c = this.categoryrepo.findById(cid).orElseThrow(()-> new ResouceNotFound("category","id",cid));
		this.categoryrepo.delete(c);
	}

	@Override
	public CategoryDto getCategory(Integer id) {
		// TODO Auto-generated method stub
		Category c = this.categoryrepo.findById(id).orElseThrow(()-> new ResouceNotFound("category","id",id));
		return this.mm.map(c, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCartegory() {
		// TODO Auto-generated method stub
		List<Category> cats = this.categoryrepo.findAll();
		List<CategoryDto> catDto = cats.stream().map((cat)->this.mm.map(cat,CategoryDto.class)).collect(Collectors.toList());
		return catDto;
	}
	

}
