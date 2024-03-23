package com.ebos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebos.tables.UserAddress;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long>{
	

}
