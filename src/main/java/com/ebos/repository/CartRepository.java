package com.ebos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebos.tables.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

}
