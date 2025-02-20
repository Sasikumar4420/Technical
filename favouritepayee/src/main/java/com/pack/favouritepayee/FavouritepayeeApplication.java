package com.pack.favouritepayee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.pack.favouritepayee.entity.Customer;
import com.pack.favouritepayee.repository.CustomerRepository;

@EnableFeignClients
@SpringBootApplication
public class FavouritepayeeApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(FavouritepayeeApplication.class, args);
	}
	
	private final CustomerRepository customerRepository;

	public FavouritepayeeApplication(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		saveCustomers();
		
	}

	private void saveCustomers() {
		List<Customer> customerList=new ArrayList<>();
		customerList.add(new Customer(1l, "komal", "komal@email.com", "komal@123"));
		customerList.add(new Customer(2l, "yash", "yash@email.com", "yash@123"));
		customerRepository.saveAll(customerList);
		
	}

}
