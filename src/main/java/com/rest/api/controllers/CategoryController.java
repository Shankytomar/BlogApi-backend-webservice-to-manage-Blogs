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
import org.springframework.web.bind.annotation.RestController;
import com.rest.api.services.CategoryService;

import jakarta.validation.Valid;

import com.rest.api.payloads.ApiResponse;
import com.rest.api.payloads.CategoryDto;

@RequestMapping("api/categories/")
@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryservice;

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto cdto) {
		CategoryDto catdto = this.categoryservice.createCategory(cdto);
		return new ResponseEntity<CategoryDto>(catdto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto cdto, @PathVariable Integer catId){
		CategoryDto catdto = this.categoryservice.updateCategory(cdto, catId);
		return new ResponseEntity<CategoryDto>(catdto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
		this.categoryservice.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
		CategoryDto cdto = this.categoryservice.getCategory(catId);
		return new ResponseEntity<CategoryDto>(cdto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		List<CategoryDto> ldto = this.categoryservice.getAllCartegory();
		return new ResponseEntity<List<CategoryDto>>(ldto,HttpStatus.OK);
	}
}
