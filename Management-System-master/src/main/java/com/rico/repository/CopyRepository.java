package com.rico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rico.entity.Copy;

public interface CopyRepository extends JpaRepository<Copy, Integer>
{
	
}
