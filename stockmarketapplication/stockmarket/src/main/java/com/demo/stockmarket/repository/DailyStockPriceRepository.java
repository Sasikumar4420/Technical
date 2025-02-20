package com.demo.stockmarket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.stockmarket.entity.DailyStockPrice;
import com.demo.stockmarket.entity.Stock;

@Repository
public interface DailyStockPriceRepository extends JpaRepository<DailyStockPrice, Long>
{
	Optional<DailyStockPrice> findFirstByStockOrderByDateDesc(Stock stock);
	
	List<DailyStockPrice> findTop5ByStockOrderByDateDesc(Stock stock);

}
