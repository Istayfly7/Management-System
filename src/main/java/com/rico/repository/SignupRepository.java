package com.rico.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rico.entity.Signup;

public interface SignupRepository extends JpaRepository<Signup, Integer>{

	ArrayList<Signup> findByUserName(String userName);

}
