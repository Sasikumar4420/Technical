package com.apps.bankingapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apps.bankingapplication.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
   Optional<Customer> findByCustomerId(Long customerId);

}
