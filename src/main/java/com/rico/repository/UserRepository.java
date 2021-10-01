package com.rico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rico.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
