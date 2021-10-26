package com.library.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.AdminUser;
import com.library.entity.User;
import com.library.entity.VisitorUser;
import com.library.repository.UserRepository;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	//========================================================== Admin ONLY Controls =====================================================================
	/*@GetMapping("/accounts")
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
	
	
	//============================================================= Additional Controls =======================================================================
	@GetMapping("/catalog")
	public ResponseEntity<List<Title>> viewCatalog(){
		try {
			List<Title> catalog = new ArrayList<>();
			titleRepository.findAll().forEach(catalog::add);
			
			if(!catalog.isEmpty()) {
				return new ResponseEntity<>(catalog, HttpStatus.OK);
			}
				
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("/putOnHold/{id}")
	public ResponseEntity<Copy> putOnHold(@RequestParam(required=true) User user, @PathVariable(name="id") int bookId){
		try {
			Optional<Copy> bookData = copyRepository.findById(bookId);
			
			if(bookData.isPresent()) {
				Copy book = bookData.get();
				user.putOnHold(book);
				
				copyRepository.save(book);
				userRepository.save(user);
				
				return new ResponseEntity<>(book, HttpStatus.OK);
			}
				
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("/checkIn/{id}")
	public ResponseEntity<Copy> checkIn(@RequestParam(required=true) User user, @PathVariable(name="id") int bookId){
		try {
			Optional<Copy> bookData = copyRepository.findById(bookId);
			
			if(bookData.isPresent()) {
				Copy book = bookData.get();
				user.checkIn(book);
				
				copyRepository.save(book);
				userRepository.save(user);
				
				return new ResponseEntity<>(book, HttpStatus.OK);
			}
				
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("/checkOut/{id}")
	public ResponseEntity<Copy> checkOut(@RequestParam(required=true) User user, @PathVariable(name="id") int bookId){
		try {
			Optional<Copy> bookData = copyRepository.findById(bookId);
			
			if(bookData.isPresent()) {
				Copy book = bookData.get();
				user.checkOut(book);
				
				copyRepository.save(book);
				userRepository.save(user);
				
				return new ResponseEntity<>(book, HttpStatus.OK);
			}
				
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("/myAccount")
	public ResponseEntity<User> getMyAccount(@RequestParam(required=true) User account) {
		try {
			Optional<User> accountData = userRepository.findById(account.getId());
			//User account = userRepository.findByuserName(userName);
			
			//checking if user account exist
			if(accountData.get() != null) {
				User u = accountData.get();
				return new ResponseEntity<>(u, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
	
	
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
	
//================================================================ For Testing Purposes ==========================================================================
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
				createNewAccount(users);
			
			return new ResponseEntity<>(usersList, HttpStatus.OK);
			
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
