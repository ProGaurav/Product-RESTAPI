package com.webapp.user.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.user.entities.User;
import com.webapp.user.exceptions.ResourceNotFoundException;
import com.webapp.user.payloads.UserDto;
import com.webapp.user.repository.UserRepository;
import com.webapp.user.services.IUserService;


@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public List<UserDto> getAllUsers() {
        List<User> users=userRepo.findAll();
		
		List<UserDto> userDtos=users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
	
		User user=dtoToUser(userDto);
		User savedUser=userRepo.save(user);		
		return userToDto(savedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		return userToDto(user);
	}

	@Override
	public void deleteUser(Integer userId) {
	User user=userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		userRepo.delete(user);

	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
	   User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
	  
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setMobileNo(userDto.getMobileNo());
		user.setEmailId(userDto.getEmailId());
		user.setPassword(userDto.getPassword());
		
		User updatedUser=userRepo.save(user);
		return userToDto(updatedUser);
	   
	}
	
	//model mapper converts userDto to users
	public User dtoToUser(UserDto userDto)
	{
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setFirstName(userDto.getFirstName());
//		user.setLastName(userDto.getLastName());
//		user.setMobileNo(userDto.getMobileNo());
//		user.setEmailId(userDto.getEmailId());
//		user.setPassword(userDto.getPassword());
//		return user;
		User user=modelMapper.map(userDto,User.class );
		return user;
	

	}
  //Model mapper converts user to userDto
	public UserDto userToDto (User user)
	{
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setFirstName(user.getFirstName());
//		userDto.setLastName(user.getLastName());
//		userDto.setMobileNo(user.getMobileNo());
//		userDto.setEmailId(user.getEmailId());
//		userDto.setPassword(user.getPassword());
//		
//		return userDto;
	
		UserDto userDto=modelMapper.map(user, UserDto.class);
		return userDto;
	}
}
