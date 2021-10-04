package com.rico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rico.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByuserName(String userName);

}
