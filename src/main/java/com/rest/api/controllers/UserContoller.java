package com.rest.api.controllers;

import java.util.List;
import java.util.Map;

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

import com.rest.api.payloads.UserDto;
import com.rest.api.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users/")
public class UserContoller {

	@Autowired
	private UserService userservice;
	
	// POST -create user
	//enable validators in controller
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userdto){
		UserDto created = this.userservice.createUser(userdto);
		return new ResponseEntity<UserDto>(created,HttpStatus.CREATED);
	}
	
	// PUT -update user
	@PutMapping("{userid}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userdto,@PathVariable("userid") Integer userid){
		UserDto ud =this.userservice.updateUser(userdto, userid);
		return ResponseEntity.ok(ud);
	}
	//DELETE -delete user
	@DeleteMapping("/{userid}")
	public ResponseEntity<?> deleteUser(@PathVariable("userid") Integer userid){
		this.userservice.deleteUser(userid);
		return ResponseEntity.ok(Map.of("msg","user deleted successfully"));
	}
	//GET -user get
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		return ResponseEntity.ok(this.userservice.getAllUserDto());
	}
	@GetMapping("/{userid}")
	public ResponseEntity<UserDto> getUser(@PathVariable("userid") Integer userid ){
		return ResponseEntity.ok(this.userservice.getUserById(userid));
	}
	
	
}
