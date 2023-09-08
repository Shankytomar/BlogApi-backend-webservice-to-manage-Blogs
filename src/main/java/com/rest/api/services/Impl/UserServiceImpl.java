package com.rest.api.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rest.api.models.User;
import com.rest.api.payloads.UserDto;
import com.rest.api.repositories.UserRepo;
import com.rest.api.services.UserService;
import com.rest.api.exceptions.ResouceNotFound;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	//here proxyclass as implementing class of interface create dynamically when program boots
	
	@Override
	public UserDto getUserById(Integer userid) {
		// TODO Auto-generated method stub
		User u = this.userRepo.findById(userid).orElseThrow(()-> new ResouceNotFound("User","id",userid));
		return this.userToDto(u);
	}

	@Override
	public UserDto createUser(UserDto ud) {
		User u = dtoToUser(ud);
		User uSaved = userRepo.save(u);
		// TODO Auto-generated method stub
		return this.userToDto(uSaved);
	}

	@Override
	public UserDto updateUser(UserDto ud, Integer userid) {
		// TODO Auto-generated method stub
		User u = this.userRepo.findById(userid).orElseThrow(()-> new ResouceNotFound("User","id",userid));
		
		u.setName(ud.getName());
		u.setAbout(ud.getAbout());
		u.setEmail(ud.getEmail());
		u.setPassword(ud.getPassword());
		User us =this.userRepo.save(u);
		return this.userToDto(us);
	}

	@Override
	public List<UserDto> getAllUserDto() {
		// TODO Auto-generated method stub
		List<User> ul = this.userRepo.findAll();
		List<UserDto> udl =ul.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return udl;
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		User u = this.userRepo.findById(id).orElseThrow(()-> new ResouceNotFound("User","id",id));
		this.userRepo.delete(u);
	}
	
	private User dtoToUser(UserDto ud) {
		User u = new User();
		u.setId(ud.getId());
		u.setName(ud.getName());
		u.setAbout(ud.getAbout());
		u.setEmail(ud.getEmail());
		u.setPassword(ud.getPassword());
		return u;
		
	}
	private UserDto userToDto(User u) {
		UserDto ud = new UserDto();
		ud.setId(u.getId());
		ud.setName(u.getName());
		ud.setAbout(u.getAbout());
		ud.setEmail(u.getEmail());
		ud.setPassword(u.getPassword());
		return ud;
		
	}


}
