package com.ebos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebos.tables.Products;


@Repository
public interface ProductRepository extends JpaRepository<Products, Long>{
	

}
