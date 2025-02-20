package com.demo.stockmarket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.stockmarket.entity.Account;
import com.demo.stockmarket.entity.DailyStockPrice;
import com.demo.stockmarket.entity.Order;
import com.demo.stockmarket.entity.OrderStatus;
import com.demo.stockmarket.entity.Stock;
import com.demo.stockmarket.entity.Trade;
import com.demo.stockmarket.entity.TransactionType;
import com.demo.stockmarket.repository.AccountRepository;
import com.demo.stockmarket.repository.DailyStockPriceRepository;
import com.demo.stockmarket.repository.OrderRepository;
import com.demo.stockmarket.repository.StockRepository;
import com.demo.stockmarket.repository.TradeRepository;

@SpringBootApplication
public class StockmarketApplication implements CommandLineRunner {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private DailyStockPriceRepository dailyStockPriceRepository;
	@Autowired
	private StockRepository stockRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private TradeRepository tradeRepository;

	public static void main(String[] args) {
		SpringApplication.run(StockmarketApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		addAccount();
		addStock();
		addStockHistory();
		

	}

	private void addTrade() {
		List<Trade> tradeList = new ArrayList<>();
		tradeList.add(new Trade(1l, new Stock(2l, "ICICI", 2001, 5.0, 2.0, 50.0), LocalDateTime.now().minusDays(2),
				new Account(1l, "venkat", "venkat@gmail.com", "venkat@123", "JUI1234", 1000.0), 5, 500.0));
		tradeList.add(new Trade(2l, new Stock(1l, "SBI", 2001, 5.0, 2.0, 50.0), LocalDateTime.now().minusDays(1),
				new Account(1l, "venkat", "venkat@gmail.com", "venkat@123", "JUI1234", 1000.0), 5, 500.0));
		tradeRepository.saveAll(tradeList);
	}

	private void addOrder() {
		List<Order> orderList = new ArrayList<>();
		orderList.add(new Order(1l, new Stock(1l, "SBI", 2001, 5.0, 2.0, 50.0), 5, 500.0, OrderStatus.EXECUTED,
				LocalDateTime.now(), TransactionType.BUY,
				new Account(2l, "vijay", "vijay@gmail.com", "vijay@xyz", "JUI12323444", 1000.0)));
		orderList.add(new Order(2l, new Stock(2l, "ICICI", 2001, 5.0, 2.0, 50.0), 5, 500.0, OrderStatus.PENDING,
				LocalDateTime.now(), TransactionType.SELL,
				new Account(1l, "venkat", "venkat@gmail.com", "venkat@123", "JUI1234", 1000.0)));
		orderRepository.saveAll(orderList);
	}

	List<Stock> stocks = new ArrayList<>();

	private void addStockHistory() {
		List<DailyStockPrice> historys = new ArrayList<>();
		historys.add(new DailyStockPrice(1l, stocks.get(0), LocalDate.now().minusDays(1), 50.0));
		historys.add(new DailyStockPrice(2l, stocks.get(0), LocalDate.now().minusDays(2), 40.0));
		historys.add(new DailyStockPrice(3l, stocks.get(0), LocalDate.now().minusDays(3), 30.0));
		historys.add(new DailyStockPrice(4l, stocks.get(0), LocalDate.now().minusDays(4), 20.0));
		historys.add(new DailyStockPrice(5l, stocks.get(0), LocalDate.now().minusDays(5), 10.0));
		historys.add(new DailyStockPrice(6l, stocks.get(1), LocalDate.now().minusDays(1), 500.0));
		historys.add(new DailyStockPrice(7l, stocks.get(1), LocalDate.now().minusDays(2), 600.0));
		historys.add(new DailyStockPrice(8l, stocks.get(1), LocalDate.now().minusDays(3), 700.0));
		historys.add(new DailyStockPrice(9l, stocks.get(1), LocalDate.now().minusDays(4), 800.0));
		historys.add(new DailyStockPrice(10l, stocks.get(1), LocalDate.now().minusDays(5), 900.0));
		historys.add(new DailyStockPrice(11l, stocks.get(2), LocalDate.now().minusDays(1), 100.0));
		historys.add(new DailyStockPrice(12l, stocks.get(2), LocalDate.now().minusDays(2), 90.0));
		historys.add(new DailyStockPrice(13l, stocks.get(2), LocalDate.now().minusDays(3), 80.0));
		historys.add(new DailyStockPrice(14l, stocks.get(2), LocalDate.now().minusDays(4), 70.0));
		historys.add(new DailyStockPrice(15l, stocks.get(2), LocalDate.now().minusDays(5), 60.0));
		dailyStockPriceRepository.saveAll(historys);
	}

	private void addStock() {
		stocks.add(new Stock(1l, "SBI", 2001, 5.0, 2.0, 50.0));
		stocks.add(new Stock(2l, "ICICI", 2001, 5.0, 2.0, 50.0));
		stocks.add(new Stock(3l, "HDFC", 2001, 5.0, 2.0, 50.0));
		stockRepository.saveAll(stocks);
	}

	private void addAccount() {
		List<Account> accounts = new ArrayList<>();
		accounts.add(new Account(1l, "venkat", "venkat@gmail.com", "venkat@123", "JUI1234", 1000.0));
		accounts.add(new Account(2l, "vijay", "vijay@gmail.com", "vijay@xyz", "JUI12323444", 1000.0));
		accounts.add(new Account(3l, "sooraj", "sooraj@gmail.com", "sethu@xyz", "JUIP98765", 1000.0));
		accounts.add(new Account(4l, "komal", "komal@gmail.com", "komal@xyz", "JUIP6754", 1000.0));
		accounts.add(new Account(5l, "yash", "yash@gmail.com", "yash@xyz", "JUIP6754", 1000.0));
		accountRepository.saveAll(accounts);
	}

}
