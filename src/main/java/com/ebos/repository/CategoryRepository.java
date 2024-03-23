package com.ebos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebos.tables.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	//Optional<Category> findByType(String categoryType);

	Optional<Category> findByCategoryType(String categoryType);

}
