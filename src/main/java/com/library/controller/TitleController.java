package com.library.controller;

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

import com.library.entity.Title;
import com.library.repository.TitleRepository;

@RestController
@RequestMapping("titles")
public class TitleController
{
	@Autowired
	private TitleRepository titleRepository;
	
	//Note: There should be a check, either here or in code calling these methods, 
	//as to whether the current logged-in user is admin, having privilege to add/remove titles
	@PostMapping("/save")
	public ResponseEntity<Title> createNewTitle(@RequestBody Title title)
	{
		try
		{
			Title t = titleRepository.save(title);
			return new ResponseEntity<>(t, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/remove/{titleid}")
	public void removeTitle(@RequestBody int titleid)
	{
		titleRepository.delete(titleRepository.getById(titleid));
	}
	@GetMapping("/show")
	public List<Title> showAll()
	{
		return titleRepository.findAll();
	}
	@GetMapping("/show/{key}")
	public List<Title> showByString(@PathVariable("key") String key)
	{
		//returns all titles containing {key}
		List<Title> all = titleRepository.findAll();
		List<Title> query = new ArrayList<>();
		for(Title t: all)
		{
			//make title and key both lowercase to eliminate case-sensitivity
			if(t.getTitle().toLowerCase().contains(key.toLowerCase()))
				query.add(t);
		}
		return query;
	}
}
