package com.demo.xyzmc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.xyzmc.entity.ConnectionStatus;
import com.demo.xyzmc.entity.Customer;
import com.demo.xyzmc.entity.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

	Optional<Request> findByRequestPhoneNumberAndCustomerAndConnectionStatus(Long mobileNumber,
			Customer customer, ConnectionStatus pending);

	Optional<Request> findByCustomerAndConnectionStatusNot(Customer customer, ConnectionStatus rejected);

}
