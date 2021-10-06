package com.rico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rico.entity.Title;

public interface TitleRepository extends JpaRepository<Title, Integer>
{
	
}
