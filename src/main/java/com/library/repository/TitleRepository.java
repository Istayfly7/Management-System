package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.Title;

public interface TitleRepository extends JpaRepository<Title, Integer>
{
	
}
