package com.rico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.rico.entity.User;
import com.rico.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@PostMapping("/save")
	public ResponseEntity<User> createNewAccount(@RequestBody User user) {
		try {
			User u = userRepository.save(user);
			return new ResponseEntity<>(u, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//NEEDS TO BE FIXED**************
	/*@GetMapping("/account")
	public ResponseEntity<List<User>> getMyAccount(@RequestParam(required=true) String userName){
		try {
			List<User> userList = new ArrayList<>();
			
			if(userName == null)
				userRepository.findAll().forEach(userList::add);
			else if(userName != null)
				userRepository.findByUserName(userName).forEach(userList::add); //NOT TESTED***********
			
			return new ResponseEntity<>(userList, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
	
}
