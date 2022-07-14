package com.gla.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "station_master")
public class StationEntity implements Serializable {

	private static final long serialVersionUID = -1798070786993154676L;

	@Id
	@GeneratedValue
	@Column(name = "station_id")
	private int stationId;

	@Column(name = "station_name")
	private String stationName;

	@Column(name = "address")
	private String address;

	@Column(name = "zipcode")
	private String zipcode;

	@Column(name = "total_capacity")
	private int totalCapacity;

	@Column(name = "created_on")
	private Date createdOn;

	@OneToMany(targetEntity = BikeEntity.class, mappedBy = "ownerStation", fetch = FetchType.LAZY)
	@ElementCollection(targetClass = BikeEntity.class)
	private Set<BikeEntity> bikes;
	
	@OneToMany(targetEntity = BookingDetails.class, mappedBy = "startLoc", fetch = FetchType.LAZY)
	@ElementCollection(targetClass = BookingDetails.class)
	private Set<BookingDetails> startBookingDetails;
	
	@OneToMany(targetEntity = BookingDetails.class, mappedBy = "dropLoc", fetch = FetchType.LAZY)
	@ElementCollection(targetClass = BookingDetails.class)
	private Set<BookingDetails> endBookingDetails;

	public StationEntity() {
		super();
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public int getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Set<BikeEntity> getBikes() {
		return bikes;
	}

	public void setBikes(Set<BikeEntity> bikes) {
		this.bikes = bikes;
	}

	public Set<BookingDetails> getStartBookingDetails() {
		return startBookingDetails;
	}

	public void setStartBookingDetails(Set<BookingDetails> startBookingDetails) {
		this.startBookingDetails = startBookingDetails;
	}

	public Set<BookingDetails> getEndBookingDetails() {
		return endBookingDetails;
	}

	public void setEndBookingDetails(Set<BookingDetails> endBookingDetails) {
		this.endBookingDetails = endBookingDetails;
	}

	@Override
	public String toString() {
		return "{\"stationId\":\"" + stationId + "\", \"stationName\":\"" + stationName + "\", \"address\":\"" + address
				+ "\", \"zipcode\":\"" + zipcode + "\", \"totalCapacity\":\"" + totalCapacity + "\", \"createdOn\":\""
				+ createdOn + "\" }";
	}

}
