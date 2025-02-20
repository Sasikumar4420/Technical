package com.pack.favouritepayee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.favouritepayee.entity.Account;
import com.pack.favouritepayee.entity.Customer;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	List<Account> findAllByCustomer(Customer customer);

	Optional<Account> findByAccountNameAndCustomer(String accountName, Customer customer);

	Optional<Account> findByIbanAccountNumberAndCustomer(String ibanAccountNumber, Customer customer);

}
