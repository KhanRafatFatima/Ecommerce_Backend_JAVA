package com.ebos.tables;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class SubCategory {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
		
		private String categoryName;
		
		@ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
		private Set<Products> products;
		
		@ManyToOne
		@JoinColumn(name="category_id")
		private Category category;
		
		public SubCategory() {
			
		}

		
		
		public SubCategory(String categoryName, Category category) {
			super();
			this.categoryName = categoryName;
			this.category = category;
		}



		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}



		public Set<Products> getProducts() {
			return products;
		}



		public void setProducts(Set<Products> products) {
			this.products = products;
		}
		
		
		
		
		
		

}
