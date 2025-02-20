package com.demo.stockmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.stockmarket.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> 
{
	
 
}
