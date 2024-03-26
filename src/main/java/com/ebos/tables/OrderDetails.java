package com.ebos.tables;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    
    

    @OneToOne
    @JoinColumn(name = "Address_id")
    private UserAddress userAddress;

    @OneToOne
    @JoinColumn(name = "Payment_id")
    private Transaction transaction;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_product",
               joinColumns = @JoinColumn(name = "order_id"),
               inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Products> products;

    private LocalDateTime orderdDateTime;
    
    public OrderDetails() {
		
	}

    
    
	public OrderDetails(Long orderId, UserAddress userAddress, Transaction transaction,
			LocalDateTime orderdDateTime) {
		super();
		this.orderId = orderId;
		this.userAddress = userAddress;
		this.transaction = transaction;
		this.orderdDateTime = orderdDateTime;
	}

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public LocalDateTime getOrderdDateTime() {
		return orderdDateTime;
	}

	public void setOrderdDateTime(LocalDateTime orderdDateTime) {
		this.orderdDateTime = orderdDateTime;
	}

	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public UserAddress getAddress_Details() {
		return userAddress;
	}

	public void setAddress_Details(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public Transaction getPayment() {
		return transaction;
	}

	public void setPayment(Transaction transaction) {
		this.transaction = transaction;
	}

	

	

}
