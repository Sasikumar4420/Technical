package com.demo.stockmarket.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.stockmarket.entity.Account;
import com.demo.stockmarket.entity.Order;
import com.demo.stockmarket.entity.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findAllByOrderStatus(OrderStatus pending);
	Page<Order> findAllByAccount(Account account, Pageable pageable);

}
