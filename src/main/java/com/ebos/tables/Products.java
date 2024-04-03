package com.ebos.tables;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.ebos.repository.Order_DetailsRepository;

@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productTitle;

    private String productDesc;

    private String productSummary;

    private Long productPrice;
    
    private LocalDateTime productCreatedDate;
    
    private LocalDateTime productUpdatedDate;
    
    private LocalDateTime productPublishedDate;
    
    private Long sellerId;

    private Long quantity;
    
    private BigDecimal discount;
    
    private LocalDateTime saleStartsDate;	
    
    private LocalDateTime salesEndDate;
    
    private boolean sales;
    
    private String productBannerImage;
    
    @ManyToMany
    @JoinTable(name="product_category",joinColumns = @JoinColumn(name="product_id"),
                                       inverseJoinColumns =@JoinColumn(name="category_id"))
    private Set<Category> categories=new HashSet<>();
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductImages> images;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductReview> reviews;
    
    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<OrderDetails> orders;

	
	public Products() {
		
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}



	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	

	public Long  getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Long  productPrice) {
		this.productPrice = productPrice;
	}
	

//	public Category getCategory() {
//		return category;
//	}
//
//	public void setCategory(Category category) {
//		this.category = category;
//	}
	
	

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductSummary() {
		return productSummary;
	}

	public void setProductSummary(String productSummary) {
		this.productSummary = productSummary;
	}

	public LocalDateTime getProductCreatedDate() {
		return productCreatedDate;
	}

	public void setProductCreatedDate(LocalDateTime productCreatedDate) {
		this.productCreatedDate = productCreatedDate;
	}

	public LocalDateTime getProductUpdatedDate() {
		return productUpdatedDate;
	}

	public void setProductUpdatedDate(LocalDateTime productUpdatedDate) {
		this.productUpdatedDate = productUpdatedDate;
	}

	public LocalDateTime getProductPublishedDate() {
		return productPublishedDate;
	}

	public void setProductPublishedDate(LocalDateTime productPublishedDate) {
		this.productPublishedDate = productPublishedDate;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public LocalDateTime getSaleStartsDate() {
		return saleStartsDate;
	}

	public void setSaleStartsDate(LocalDateTime saleStartsDate) {
		this.saleStartsDate = saleStartsDate;
	}

	public LocalDateTime getSalesEndDate() {
		return salesEndDate;
	}

	public void setSalesEndDate(LocalDateTime salesEndDate) {
		this.salesEndDate = salesEndDate;
	}
	
	

	public String getProductBannerImage() {
		return productBannerImage;
	}

	public void setProductBannerImage(String productBannerImage) {
		this.productBannerImage = productBannerImage;
	}
	
	

	public boolean isSales() {
		return sales;
	}

	public void setSales(boolean sales) {
		this.sales = sales;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<ProductReview> getReviews() {
		return reviews;
	}

	public void setReviews(Set<ProductReview> reviews) {
		this.reviews = reviews;
	}

	public Set<OrderDetails> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderDetails> orders) {
		this.orders = orders;
	}

	public Set<ProductImages> getImages() {
		return images;
	}

	public void setImages(Set<ProductImages> images) {
		this.images = images;
	}

	public Products(String productTitle, String productDesc, String productSummary, Long productPrice,
			LocalDateTime productCreatedDate, LocalDateTime productUpdatedDate, LocalDateTime productPublishedDate,
			Long sellerId, Long quantity, BigDecimal discount, LocalDateTime saleStartsDate, LocalDateTime salesEndDate,
			Set<Category> categories,String productBannerImage,boolean sales) {
		super();
		this.productTitle = productTitle;
		this.productDesc = productDesc;
		this.productSummary = productSummary;
		this.productPrice = productPrice;
		this.productCreatedDate = productCreatedDate;
		this.productUpdatedDate = productUpdatedDate;
		this.productPublishedDate = productPublishedDate;
		this.sellerId = sellerId;
		this.quantity = quantity;
		this.discount = discount;
		this.saleStartsDate = saleStartsDate;
		this.salesEndDate = salesEndDate;
		this.categories = categories;
		this.productBannerImage=productBannerImage;
		this.sales=sales;
	}

	


	
	

}
