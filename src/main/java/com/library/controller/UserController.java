package com.library.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.AdminUser;
import com.library.entity.Copy;
import com.library.model.Title;
import com.library.entity.User;
import com.library.entity.VisitorUser;
import com.library.repository.CopyRepository;
import com.library.repository.UserRepository;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CopyRepository copyRepository;
	
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
	}*/
	
	
	//============================================================= Additional Controls =======================================================================
	
	/*@GetMapping("/copies")
	public ResponseEntity<List<Copy>> viewAllCopies(){
		try {
			List<Copy> catalog = new ArrayList<>();
			copyRepository.findAll().forEach(catalog::add);
			
			if(!catalog.isEmpty()) {
				return new ResponseEntity<>(catalog, HttpStatus.OK);
			}
				
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
	
	
	/*@PutMapping("/putOnHold/{id}")
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
	
	@GetMapping("/viewAllUsers")
	public ResponseEntity<List<User>> viewUsers(){
		try {
			List<User> users = new ArrayList<>();
			userRepository.findAll().forEach(users::add);
			
			if(!users.isEmpty()) {
				return new ResponseEntity<>(users, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/catalogAll")
	public ResponseEntity<List<Copy>> viewCopies()
	{
		try {
			List<Copy> copies = new ArrayList<>();
			copyRepository.findAll().forEach(copies::add);
			
			if(!copies.isEmpty())
				return new ResponseEntity<>(copies, HttpStatus.OK);
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/catalog")
	public ResponseEntity<List<Title>> viewTitles(){
		try {
			List<Title> catalog = Title.getTitles();
			
			if(!catalog.isEmpty()) {
				return new ResponseEntity<>(catalog, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/addBook")
	public ResponseEntity<Copy> createNewBook(@RequestBody Copy copy)
	{
		try {
			if(copy != null) {
				Copy c = copyRepository.save(copy);
				return new ResponseEntity<>(c, HttpStatus.OK);
			}
			
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
	
//================================================================ For Testing Purposes ==========================================================================
	@PostMapping("/save-defaultAccounts")
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
	
	
	@PostMapping("/save-defaultBook")
	public ResponseEntity<Copy> createDefaultBook()
	{
		try
		{
			Title title = new Title("Lord of The Rings", "Whoever", Date.valueOf(LocalDate.of(2000, 3, 25)));
			
			Copy book = new Copy(title);
			
			return createNewBook(book);
			
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
