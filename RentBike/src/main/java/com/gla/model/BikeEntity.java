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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bike_master")
public class BikeEntity implements Serializable {

	private static final long serialVersionUID = -1798070786993154676L;

	@Id
	@GeneratedValue
	@Column(name = "bike_id")
	private int bikeId;

	@Column(name = "bike_number")
	private String bikeNumber;

	@Column(name = "model_name")
	private String modelName;

	@Column(name = "spec")
	private String spec;

	@ManyToOne
	@JoinColumn(name = "owner_station")
	private StationEntity ownerStation;

	@Column(name = "bike_status")
	private String bikeStatus;

		@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "created_by")
	private String createdBy;

	@OneToMany(targetEntity = RepairRequest.class, mappedBy = "bike", fetch = FetchType.LAZY)
	@ElementCollection(targetClass = RepairRequest.class)
	private Set<RepairRequest> repReq;
	
	@OneToMany(targetEntity = BookingDetails.class, mappedBy = "bike", fetch = FetchType.LAZY)
	@ElementCollection(targetClass = BookingDetails.class)
	private Set<BookingDetails> bookingDetails;

	public BikeEntity() {
		super();
	}

	public int getBikeId() {
		return bikeId;
	}

	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}

	public String getBikeNumber() {
		return bikeNumber;
	}

	public void setBikeNumber(String bikeNumber) {
		this.bikeNumber = bikeNumber;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public StationEntity getOwnerStation() {
		return ownerStation;
	}

	public void setOwnerStation(StationEntity ownerStation) {
		this.ownerStation = ownerStation;
	}

	public String getBikeStatus() {
		return bikeStatus;
	}

	public void setBikeStatus(String bikeStatus) {
		this.bikeStatus = bikeStatus;
	}

	
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Set<RepairRequest> getRepReq() {
		return repReq;
	}

	public void setRepReq(Set<RepairRequest> repReq) {
		this.repReq = repReq;
	}
	
	public Set<BookingDetails> getBookingDetails() {
		return bookingDetails;
	}
	public void setBookingDetails(Set<BookingDetails> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	@Override
	public String toString() {
		return "{\"bikeId\":\"" + bikeId + "\", \"bikeNumber\":\"" + bikeNumber + "\", \"modelName\":\"" + modelName
				+ "\", \"spec\":\"" + spec + "\", \"bikeStatus\":\""
				+ bikeStatus + "\", \"createdOn\":\"" + createdOn + "\", \"createdBy\":\"" + createdBy + "\"}";
	}
}
