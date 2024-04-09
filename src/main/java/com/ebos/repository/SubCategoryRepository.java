package com.ebos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebos.tables.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {

	Optional<SubCategory> findByCategoryName(String categoryName);

}
