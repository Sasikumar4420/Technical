package com.pack.voterdetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pack.voterdetails.entity.Users;
import com.pack.voterdetails.repository.UsersRepository;

@SpringBootApplication
public class VoterdetailsApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(VoterdetailsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		saveUsers();
		
	}
	private final UsersRepository usersRepository;
	
	public VoterdetailsApplication(UsersRepository usersRepository) {
		super();
		this.usersRepository = usersRepository;
	}

	private void saveUsers() {
		List<Users> userList=new ArrayList<>();
		userList.add(new Users(1l,"komal",LocalDate.of(2001, 12, 31),"komal@gmail.com","komal@123"));
		userList.add(new Users(2l,"pavan",LocalDate.of(2021, 1, 21),"pavan@gmail.com","komal@123"));
		userList.add(new Users(3l,"suresh",LocalDate.of(2000, 8, 22),"suresh@gmail.com","komal@123"));
		userList.add(new Users(4l,"vijay",LocalDate.of(2011, 2, 11),"vijay@gmail.com","komal@123"));
		userList.add(new Users(5l,"yash",LocalDate.of(2018, 7, 10),"yash@gmail.com","komal@123"));
		userList.add(new Users(6l,"venkat",LocalDate.of(1999, 12, 31),"venkat@gmail.com","komal@123"));
		usersRepository.saveAll(userList);
	}

}
