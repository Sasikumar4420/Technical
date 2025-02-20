package com.hcl.bankApplication;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hcl.bankApplication.entity.Transactions;
import com.hcl.bankApplication.entity.User;
import com.hcl.bankApplication.repository.TransactionsRepository;
import com.hcl.bankApplication.repository.UserRepository;

@SpringBootApplication
public class BankApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}
	@Autowired
	UserRepository userRepo;
	@Autowired
	TransactionsRepository transactionsRepo;
	
	@Override
	public void run(String... args) throws Exception {
//		saveUser();
//		saveTransactions();
		
	}

	private void saveTransactions() {
		// TODO Auto-generated method stub
		Optional<User> u=userRepo.findById(1l);
		LocalDate d=LocalDate.now();
		Transactions t=new Transactions(1l,d,"credit",2000.00,"nothing",1000.00,u.get());
		transactionsRepo.save(t);
		
	}

	private void saveUser() {
		// TODO Auto-generated method stub
		User user=new User(2l,"yashvanth",1234567l,5000.00,"yash@gmail.com");
		userRepo.save(user);
	}

}
