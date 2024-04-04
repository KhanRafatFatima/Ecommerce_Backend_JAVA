package com.ebos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ebos.tables.Category;
import com.ebos.tables.Products;


@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    // Add custom queries if needed
    Optional<Long> findProductPriceByProductId(Long productId);


	List<Products> findByProductTitle(String productTitle);

    //Optional<Category> findByCategoryName(String categoryName);
//
//	List<Products> findByCategory(Category category);
}

 