package com.demo.xyzmc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.xyzmc.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
