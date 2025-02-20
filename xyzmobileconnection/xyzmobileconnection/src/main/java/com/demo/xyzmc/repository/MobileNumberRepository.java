package com.demo.xyzmc.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.xyzmc.entity.MobileNumber;
import com.demo.xyzmc.entity.Status;

@Repository
public interface MobileNumberRepository extends JpaRepository<MobileNumber, Long> {

	Page<MobileNumber> findAllByNumberStatus(Status available,Pageable pageable);

	Optional<MobileNumber> findByMobileNumberAndNumberStatus(Long mobileNumber, Status available);

}
