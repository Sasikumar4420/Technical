package com.hcl.bankApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.bankApplication.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	Optional<User> findByEmail(String email);

}
