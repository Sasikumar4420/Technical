package com.demo.stockmarket.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.stockmarket.entity.Account;
import com.demo.stockmarket.entity.Stock;
import com.demo.stockmarket.entity.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long>{

	Optional<Trade> findByStockId(Stock stock);
	Page<Trade> findAllByAccountId(Account account, Pageable pageable);

}
