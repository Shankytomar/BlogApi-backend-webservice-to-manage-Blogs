package com.rest.api.services;

import java.util.List;
import com.rest.api.payloads.UserDto;

public interface UserService{

	public UserDto createUser(UserDto ud);
	public UserDto updateUser(UserDto ud, Integer udid);
	public UserDto getUserById(Integer userid);
	public  List<UserDto> getAllUserDto();
	public void deleteUser(Integer id);
	
}
