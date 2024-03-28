package com.ebos.tables;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String typeCreditDebit;
    
    private String mode;

    private LocalDateTime createdDate;
    
    private LocalDateTime updatedDate;

    private Long amount;

    private String status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderDetails orderDetails;

    // Constructors, getters, and setters...

    
    public Transaction() {
    	
    }

	
	

	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getTypeCreditDebit() {
		return typeCreditDebit;
	}

	public void setTypeCreditDebit(String typeCreditDebit) {
		this.typeCreditDebit = typeCreditDebit;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public OrderDetails getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public OrderDetails getOrder() {
		return orderDetails;
	}

	public void setOrder(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}

	
	

	public Transaction(String typeCreditDebit, String mode, LocalDateTime createdDate, LocalDateTime updatedDate,
			Long amount, String status, User user, OrderDetails orderDetails) {
		super();
		this.typeCreditDebit = typeCreditDebit;
		this.mode = mode;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.amount = amount;
		this.status = status;
		this.user = user;
		this.orderDetails = orderDetails;
	}

	

}
