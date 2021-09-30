package com.rico.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rico.entity.User;
import com.rico.model.Visitor;
import com.rico.repository.UserRepository;

@Controller
@RequestMapping("/visitor")
public class VisitorController extends UserController{
	
	@Autowired
	private UserRepository userRepository;
	
	
	@PostMapping("/save-default")
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
	}
	
}
