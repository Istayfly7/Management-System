package com.rico.controller;

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

import com.rico.entity.Copy;
import com.rico.repository.CopyRepository;

@RestController
@RequestMapping("copies")
public class CopyController
{
	@Autowired
	private CopyRepository copyRepository;
	
	//Note: There should be a check, either here or in code calling these methods, 
	//as to whether the current logged-in user is admin, having privilege to add/remove copies
	@PostMapping("/save")
	public ResponseEntity<Copy> createNewCopy(@RequestBody Copy copy)
	{
		try
		{
			Copy c = copyRepository.save(copy);
			return new ResponseEntity<>(c, HttpStatus.OK);
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
	public List<Copy> showAll()
	{
		return copyRepository.findAll();
	}
	@GetMapping("/show/{isbn}")
	public List<Copy> showByISBN(@PathVariable("isbn") int isbn)
	{
		//returns all copies with the corresponding ISBN
		List<Copy> allCopies = copyRepository.findAll();
		List<Copy> query = new ArrayList<>();
		for(Copy c: allCopies)
		{
			if(c.getTitle().getISBN() == isbn)
				query.add(c);
		}
		return query;
	}
}
