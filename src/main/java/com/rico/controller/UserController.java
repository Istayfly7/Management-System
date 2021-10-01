package com.rico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rico.entity.User;
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
	
	/*@PostMapping("/save-default")
	public ResponseEntity<List<User>> createDefaultAccounts(){
		try {
			//Creating 3 Visitors for testing
			User acc1 = new Visitor("flyHigher17", "word");
			User acc2 = new Visitor("superhotfire", "1234");
			User acc3 = new Visitor("toosmooth", "password1234");
			
			
			List<User> accountsList = Arrays.asList(acc1, acc2, acc3);
			for(User account:accountsList)
				userRepository.save(account);
			
			return new ResponseEntity<>(accountsList, HttpStatus.OK);
			
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
	
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
