package com.rico.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.rico.entity.Signup;
import com.rico.repository.SignupRepository;

@Controller
@RequestMapping("/sign-up")
public class SignupController {
	
	@Autowired
	private SignupRepository signupRepository;
	
	
	@GetMapping("/")
	public ResponseEntity<List<Signup>> getAllAccounts(@RequestParam(required=false) String accName){
		try {
			List<Signup> signupList = new ArrayList<>();
			
			if(accName == null)
				signupRepository.findAll().forEach(signupList::add);
			else if(accName != null)
				signupRepository.findByAccName(accName).forEach(signupList::add);
				
			return new ResponseEntity<>(signupList, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/save")
	public ResponseEntity<Signup> createNewAccount(@RequestBody Signup signup) {
		try {
			Signup sig = signupRepository.save(signup);
			return new ResponseEntity<>(sig, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/save-default")
	public ResponseEntity<List<Signup>> createDefaultAccounts(){
		try {
			Signup acc1 = new Signup("istayfly7", "porter.rico@yahoo.com", "password");
			Signup acc2 = new Signup("superhotfire", "Abraham.c@gmail.com", "1234");
			Signup acc3 = new Signup("toosmooth", "Thomas.cool@hotmail.com", "password1234");
			
			
			List<Signup> accountsList = Arrays.asList(acc1, acc2, acc3);
			for(Signup account:accountsList)
				signupRepository.save(account);
			
			return new ResponseEntity<>(accountsList, HttpStatus.OK);
			
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//NOT TESTED**********************************8
	@PutMapping("/update/{id}")
	public ResponseEntity<Signup> updateAccount(@RequestBody Signup signup, @PathVariable(name="id") int acc_id){
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
