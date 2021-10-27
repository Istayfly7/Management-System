package com.library.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.Copy;
import com.library.entity.Title;
import com.library.repository.CopyRepository;
import com.library.repository.TitleRepository;

@RestController
@RequestMapping("copies")
public class CopyController
{
	@Autowired
	private CopyRepository copyRepository;
	
	@Autowired
	private TitleRepository titleRepository;


	//Note: There should be a check, either here or in code calling these methods, 
	//as to whether the current logged-in user is admin, having privilege to add/remove copies
	@PostMapping("/save")
	public ResponseEntity<Copy> createNewCopy(@RequestBody Copy copy)
	{
		try
		{
			if(copy != null) {
				if(!titleRepository.existsById(copy.getTitle().getISBN()))
					titleRepository.save(copy.getTitle());
				Copy c = copyRepository.save(copy);
				return new ResponseEntity<>(c, HttpStatus.OK);
			}
			
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping("/remove/{copyid}")
	public void removeCopy(@RequestBody int copyid)
	{
		copyRepository.delete(copyRepository.getById(copyid));
	}
	
	
	@GetMapping("/show")
	public ResponseEntity<List<Copy>> showAll()
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
	
	
	@GetMapping("/show/{isbn}")
	public ResponseEntity<List<Copy>> showByISBN(@PathVariable("isbn") int isbn)
	{
		try {
			//returns all copies with the corresponding ISBN
			List<Copy> allCopies = copyRepository.findAll();
			List<Copy> query = new ArrayList<>();
			for(Copy c: allCopies)
			{
				if(c.getTitle().getISBN() == isbn)
					query.add(c);
			}
			
			if(!query.isEmpty())
				return new ResponseEntity<>(query, HttpStatus.OK);
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//====================================================================================================================================================================================
	@PostMapping("/save-default")
	public ResponseEntity<List<Copy>> createDefaultCatalog()
	{
		try
		{
			Title t1 = new Title("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", Date.valueOf(LocalDate.of(2001, 4, 25)));
			Title t2 = new Title("Night Hoops","Carl Deuker", Date.valueOf(LocalDate.of(2000, 8, 20)));
			
			Copy c1 = new Copy(t1);
			Copy c2 = new Copy(t1);
			Copy c3 = new Copy(t2);
			createNewCopy(c1);
			createNewCopy(c2);
			createNewCopy(c3);
			
			List<Copy> books = new ArrayList<>();
			books.add(c1);
			books.add(c2);
			books.add(c3);
			
			return new ResponseEntity<>(books, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
