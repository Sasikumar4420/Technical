package com.pack.parkingspot.entity;

import com.pack.parkingspot.enums.CheckAvailability;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="parking_spots")
public class ParkingSlots {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String towerName;
	private Integer slotNumber;
	@Enumerated(EnumType.STRING)
	private CheckAvailability checkAvailability;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTowerName() {
		return towerName;
	}
	public void setTowerName(String towerName) {
		this.towerName = towerName;
	}
	public Integer getSlotNumber() {
		return slotNumber;
	}
	public void setSlotNumber(Integer slotNumber) {
		this.slotNumber = slotNumber;
	}
	
	public CheckAvailability getCheckAvailability() {
		return checkAvailability;
	}
	public void setCheckAvailability(CheckAvailability checkAvailability) {
		this.checkAvailability = checkAvailability;
	}
	
	public ParkingSlots(Integer id, String towerName, Integer slotNumber, CheckAvailability checkAvailability) {
		super();
		this.id = id;
		this.towerName = towerName;
		this.slotNumber = slotNumber;
		this.checkAvailability = checkAvailability;
	}
	public ParkingSlots() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
