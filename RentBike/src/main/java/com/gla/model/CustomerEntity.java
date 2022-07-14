package com.gla.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Table(name = "user_master")
public class CustomerEntity implements Serializable {

	private static final long serialVersionUID = -1798070786993154676L;

	@Id
	@GeneratedValue
	@Column(name = "cust_id")
	private Integer custId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "ph_no")
	private String phNo;

	@Column(name = "password")
	private String password;

	@Column(name = "email_id", unique = true)
	private String emailId;

	@Column(name = "gender")
	private String gender;

	@Column(name = "DOB")
	private String dob;

	@Column(name = "role")
	private String role;

	@Column(name = "created_on")
	private Date createdOn;

	@OneToMany(targetEntity = RepairRequest.class, mappedBy = "reqBy", fetch = FetchType.LAZY)
	@ElementCollection(targetClass = RepairRequest.class)
	private List<RepairRequest> repReq;
	
	@OneToMany(targetEntity = RepairRequest.class, mappedBy = "repairedBy", fetch = FetchType.LAZY)
	@ElementCollection(targetClass = RepairRequest.class)
	private List<RepairRequest> repaired;
	
	@OneToMany(targetEntity = BookingDetails.class, mappedBy = "customer", fetch = FetchType.LAZY)
	@ElementCollection(targetClass = BookingDetails.class)
	private Set<BookingDetails> bookingDetails;

	public CustomerEntity() {
		super();
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhNo() {
		return phNo;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public List<RepairRequest> getRepReq() {
		return repReq;
	}

	public void setRepReq(List<RepairRequest> repReq) {
		this.repReq = repReq;
	}

	public List<RepairRequest> getRepaired() {
		return repaired;
	}

	public void setRepaired(List<RepairRequest> repaired) {
		this.repaired = repaired;
	}
	
	public Set<BookingDetails> getBookingDetails() {
		return bookingDetails;
	}
	public void setBookingDetails(Set<BookingDetails> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	@Override
	public String toString() {
		return "{\"custId\":\"" + custId + "\", \"firstName\":\"" + firstName + "\", \"lastName\":\"" + lastName
				+ "\", \"phNo\":\"" + phNo + "\", \"password\":\"" + password + "\", \"emailId\":\"" + emailId
				+ "\", \"gender\":\"" + gender + "\", \"dob\":\"" + dob + "\", \"role\":\"" + role
				+ "\", \"createdOn\":\"" + createdOn + "\" }";
	}
	

}
