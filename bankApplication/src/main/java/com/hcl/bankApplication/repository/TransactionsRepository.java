package com.hcl.bankApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.bankApplication.entity.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Long>{
//	@Query("select t from Transactions t where MONTH(t.date) = : month And YEAR(t.date)=:year")
//	List<Transactions> findByMonthAndYear(@Param("month") int month,@Param("year") int year);
}
