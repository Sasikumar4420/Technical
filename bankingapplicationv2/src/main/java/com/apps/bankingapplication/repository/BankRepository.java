package com.apps.bankingapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apps.bankingapplication.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Long>{

	Optional<Bank> findByIban(String formatIban);

}
