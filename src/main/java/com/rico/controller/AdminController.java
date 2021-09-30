package com.rico.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rico.entity.User;
import com.rico.model.Admin;
import com.rico.model.Visitor;
import com.rico.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public abstract class AdminController extends UserController{
	
	@Autowired
	private UserRepository userRepository;
	
	//Initialization Block for Admin
	{
		userRepository.save(new Admin("istayfly7", "password"));
	}
	
	
	@PostMapping("/save-as-visitor")
	public ResponseEntity<User> createNewAccount(@RequestBody Visitor visitor) {
		try {
			User u = userRepository.save(visitor);
			return new ResponseEntity<>(u, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/accounts")
	public ResponseEntity<List<User>> getAllAccounts(){
		try {
			List<User> userList = new ArrayList<>();
			userRepository.findAll().forEach(userList::add);
			
			return new ResponseEntity<>(userList, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateAccount(@RequestBody User user, @PathVariable("id") int id){
		try {
			Optional<User> userData = userRepository.findById(id);
			
			if(userData.isPresent()) {
				User _user = userData.get();
				
				_user.setUserName(user.getUserName());
				_user.setPassword(user.getPassword());
				
				User u = userRepository.save(_user);
				
				return new ResponseEntity<>(u, HttpStatus.OK);
			}
			else
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
