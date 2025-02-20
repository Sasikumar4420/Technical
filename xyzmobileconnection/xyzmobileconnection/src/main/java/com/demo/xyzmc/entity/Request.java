package com.demo.xyzmc.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "requests")
public class Request {
	@Id
	private Long requestId;
	private LocalDateTime requestTime;
	private Long requestPhoneNumber;
	@ManyToOne
	@JoinColumn(name = "talk_time_id")
	private TalkTimePlan talkTime;
	@Enumerated(EnumType.STRING)
	private ConnectionStatus connectionStatus;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

}
