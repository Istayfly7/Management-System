package com.rico.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	@GetMapping("/accounts")
	public ResponseEntity<List<User>> getAllAccounts(@RequestParam(required=true) AdminUser account) {
		try {
			List<User> accountsList = new ArrayList<>();
			Optional<User> accountData = userRepository.findById(account.getId());
			
			//checking if admin account exist
			if(accountData.get() != null) {
				User u = accountData.get();
				
				if((u.getUserName() == account.getUserName()) && (u.getPassword() == account.getPassword())) {
					userRepository.findAll().forEach(accountsList::add);
				
					return new ResponseEntity<>(accountsList, HttpStatus.OK);
				}

			}
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
			
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/myaccount")
	public ResponseEntity<User> getMyAccount(@RequestParam(required=true) User account) {
		try {
			Optional<User> accountData = userRepository.findById(account.getId());
			//User account = userRepository.findByuserName(userName);
			
			//checking if user account exist
			if(accountData.get() != null) {
				User u = accountData.get();
				return new ResponseEntity<>(u, HttpStatus.OK);
			}
			else
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
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
			acc1.setUserName("istayfly");
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
