package com.webapp.user.services;

import java.util.List;

import com.webapp.user.payloads.UserDto;



public interface IUserService {

	 List<UserDto> getAllUsers();
	
	UserDto createUser(UserDto userDto);
	
	UserDto getUserById(Integer userId);
	
	void deleteUser(Integer userId);
	
	 UserDto updateUser(UserDto userDto,Integer userId);
	
	
}

