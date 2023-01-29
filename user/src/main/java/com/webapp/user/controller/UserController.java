package com.webapp.user.controller;

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

import com.webapp.user.payloads.ApiResponse;
import com.webapp.user.payloads.UserDto;
import com.webapp.user.services.IUserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private IUserService userService;
	
	//Post- create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUserDto=userService.createUser(userDto);
		
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
		
	}
	
	//Put- Update User
	
	@PutMapping("/{userId}")
  public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable Integer userId)
  {
	 UserDto updatedUserDto=userService.updateUser(userDto, userId);
	 return ResponseEntity.ok(updatedUserDto);
  }
	
	//Delete- deleteUser
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer userId)
	{
		userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true),HttpStatus.OK);
		 
	}
	
	//Get - GetAllUsers
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	//Get- getUSerById
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId)
	{
		return ResponseEntity.ok(userService.getUserById(userId));
	}
	
}
