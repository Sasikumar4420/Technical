package com.demo.movieticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.movieticket.entity.Shows;

public interface ShowsReposiotry extends JpaRepository<Shows, Long>{

}
