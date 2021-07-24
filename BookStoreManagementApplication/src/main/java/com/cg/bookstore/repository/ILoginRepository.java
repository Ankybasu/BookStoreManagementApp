package com.cg.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.Login;

@Repository
public interface ILoginRepository extends JpaRepository<Login, Integer>{
	public Login findByEmail(String email);
	public Login findByPassword(String password);
	

}
