package com.ebos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebos.tables.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	boolean existsByMobileNo(Long mobileNo);

	Optional<User> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail2);
	
	
//    Optional<User> findByEmail(String email);
//
//    Optional<User> findByUsernameOrEmail(String username, String email);
//
//    List<User> findByIdIn(List<Long> userIds);
//
//    Optional<User> findByUsername(String username);
//
//    Boolean existsByUsername(String username);
//
//    Boolean existsByEmail(String email);
//
//    Boolean existsByMobileNo(Long mobileNo);
}
