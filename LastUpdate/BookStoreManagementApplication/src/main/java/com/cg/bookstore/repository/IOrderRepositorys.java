package com.cg.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.OOrder;

@Repository
public interface IOrderRepositorys extends JpaRepository<OOrder, Integer>{

}
