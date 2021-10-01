package com.rico.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rico.entity.AdminUser;
import com.rico.entity.User;
import com.rico.entity.VisitorUser;
import com.rico.repository.UserRepository;

@RestController
@RequestMapping("users")
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
	
	@PostMapping("/save-default")
	public ResponseEntity<List<User>> createDefaultAccounts(){
		try {
			//Create a Admin by default
			User acc1 = new AdminUser();
			acc1.setUserName("flyHigher17");
			acc1.setPassword("word");
			
			//Create 2 Visitors by default
			User acc2 = new VisitorUser();
			acc2.setUserName("superhotfire");
			acc2.setPassword("1234");
			
			User acc3 = new VisitorUser();
			acc3.setUserName("toosmooth");
			acc3.setPassword("password1234");
			
			
			List<User> usersList = Arrays.asList(acc1, acc2, acc3);
			for(User users:usersList)
				userRepository.save(users);
			
			return new ResponseEntity<>(usersList, HttpStatus.OK);
			
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
