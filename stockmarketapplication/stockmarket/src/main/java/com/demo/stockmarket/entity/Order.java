package com.demo.stockmarket.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	@ManyToOne
	@JoinColumn(name = "stock_id")
	private Stock stock;
	private Integer units;
	private Double totalCost;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	private LocalDateTime orderedDate;
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDateTime getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(LocalDateTime orderedDate) {
		this.orderedDate = orderedDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public TransactionType getTransactionStatus() {
		return transactionType;
	}

	public void setTransactionStatus(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Order(Long orderId, Stock stock, Integer units, Double totalCost, OrderStatus orderStatus,
			LocalDateTime orderedDate, TransactionType transactionType, Account account) {
		super();
		this.orderId = orderId;
		this.stock = stock;
		this.units = units;
		this.totalCost = totalCost;
		this.orderStatus = orderStatus;
		this.orderedDate = orderedDate;
		this.transactionType = transactionType;
		this.account = account;
	}

	public Order() {
		super();
	}

}
