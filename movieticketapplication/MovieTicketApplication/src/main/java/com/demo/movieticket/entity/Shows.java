package com.demo.movieticket.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data

public class Shows {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long showId;
	private String showName;
	private LocalDateTime showDateTime;

	public Shows(Long showId, String showName, LocalDateTime showDateTime) {
		super();
		this.showId = showId;
		this.showName = showName;
		this.showDateTime = showDateTime;
	}

	public Shows() {
		super();
		// TODO Auto-generated constructor stub
	}

}
