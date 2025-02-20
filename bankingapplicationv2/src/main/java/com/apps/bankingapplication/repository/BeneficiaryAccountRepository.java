package com.apps.bankingapplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apps.bankingapplication.entity.BeneficiaryAccount;
import com.apps.bankingapplication.entity.Customer;
@Repository
public interface BeneficiaryAccountRepository extends JpaRepository<BeneficiaryAccount, Long> {

	Optional<BeneficiaryAccount> findByIban(String iban);

	//List<BeneficiaryAccount> findById(Optional<Customer> customer);

	
	List<BeneficiaryAccount> findAllByCustomer(Customer customer);

}
