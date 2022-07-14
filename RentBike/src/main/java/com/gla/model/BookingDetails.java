package com.gla.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "booking_details")
public class BookingDetails {

	private int bookingId;
	private String booking_no;
	private CustomerEntity customer;
	private BikeEntity bike;
	private double totalCost;
	private Date bookingDate;
	private Date dropDate;
	private String bookingStatus;
	private StationEntity startLoc;
	private StationEntity dropLoc;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	@Column(name = "booking_no")
	public String getBooking_no() {
		return booking_no;
	}
	public void setBooking_no(String booking_no) {
		this.booking_no = booking_no;
	}
	
	@ManyToOne
    @JoinColumn(name = "cust_id")
	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	@ManyToOne
    @JoinColumn(name = "bike_id")
	public BikeEntity getBike() {
		return bike;
	}

	public void setBike(BikeEntity bike) {
		this.bike = bike;
	}
	
	@Column(name = "total_cost")
	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	@Column(name = "booking_date_time")
	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Column(name = "drop_date_time")
	public Date getDropDate() {
		return dropDate;
	}
	public void setDropDate(Date dropDate) {
		this.dropDate = dropDate;
	}

	@Column(name = "booking_status")
	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	@ManyToOne
    @JoinColumn(name = "pickup_station")
	public StationEntity getStartLoc() {
		return startLoc;
	}

	public void setStartLoc(StationEntity startLoc) {
		this.startLoc = startLoc;
	}

	@ManyToOne
    @JoinColumn(name = "drop_station")
	public StationEntity getDropLoc() {
		return dropLoc;
	}

	public void setDropLoc(StationEntity dropLoc) {
		this.dropLoc = dropLoc;
	}

	@Override
	public String toString() {
		return "{\"bookingId\":\"" + bookingId + "\", \"booking_no\":\"" + booking_no + "\", \"totalCost\":\""
				+ totalCost + "\", \"bookingDate\":\"" + bookingDate + "\", \"dropDate\":\"" + dropDate
				+ "\", \"bookingStatus\":\"" + bookingStatus + "\" }";
	}
}
