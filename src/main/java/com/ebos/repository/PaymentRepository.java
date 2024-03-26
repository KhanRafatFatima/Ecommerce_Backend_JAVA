package com.ebos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebos.tables.Transaction;

@Repository
public interface PaymentRepository extends JpaRepository<Transaction, Long> {

}
