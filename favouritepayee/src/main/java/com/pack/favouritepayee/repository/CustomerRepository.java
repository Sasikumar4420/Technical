package com.pack.favouritepayee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.favouritepayee.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
