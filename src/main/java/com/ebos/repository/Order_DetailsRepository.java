package com.ebos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebos.tables.OrderDetails;
import com.ebos.tables.Products;

@Repository
public interface Order_DetailsRepository extends JpaRepository<OrderDetails, Long> {

	void save(Products products);
  
}
