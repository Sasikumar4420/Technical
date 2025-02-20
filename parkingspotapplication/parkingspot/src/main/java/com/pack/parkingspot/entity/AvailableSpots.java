package com.pack.parkingspot.entity;

import java.time.LocalDate;

import com.pack.parkingspot.enums.SlotStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class AvailableSpots {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="parkingslot")
	private ParkingSlots parkingSlot;
	private LocalDate slotAvailableFrom;
	private LocalDate slotAvailableTo;
	@Enumerated(EnumType.STRING)
	private SlotStatus status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public LocalDate getSlotAvailableFrom() {
		return slotAvailableFrom;
	}
	public void setSlotAvailableFrom(LocalDate slotAvailableFrom) {
		this.slotAvailableFrom = slotAvailableFrom;
	}
	public LocalDate getSlotAvailableTo() {
		return slotAvailableTo;
	}
	public void setSlotAvailableTo(LocalDate slotAvailableTo) {
		this.slotAvailableTo = slotAvailableTo;
	}
	
	public ParkingSlots getParkingSlot() {
		return parkingSlot;
	}
	public void setParkingSlot(ParkingSlots parkingSlot) {
		this.parkingSlot = parkingSlot;
	}
	public SlotStatus getStatus() {
		return status;
	}
	public void setStatus(SlotStatus status) {
		this.status = status;
	}
	public AvailableSpots() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AvailableSpots(Integer id, ParkingSlots parkingSlot, LocalDate slotAvailableFrom, LocalDate slotAvailableTo,
			SlotStatus status) {
		super();
		this.id = id;
		this.parkingSlot = parkingSlot;
		this.slotAvailableFrom = slotAvailableFrom;
		this.slotAvailableTo = slotAvailableTo;
		this.status = status;
	}
	
	
}
