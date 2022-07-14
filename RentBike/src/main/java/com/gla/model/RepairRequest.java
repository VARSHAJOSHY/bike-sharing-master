package com.gla.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "repair_request")
public class RepairRequest implements Serializable {
	private static final long serialVersionUID = -1798070786993154676L;

	@Id
	@GeneratedValue
	@Column(name = "req_no")
	private int reqNo;

	@ManyToOne
	@JoinColumn(name = "bike_id")
	private BikeEntity bike;

	@ManyToOne
	@JoinColumn(name = "request_by")
	private CustomerEntity reqBy;

	@Column(name = "created_datetime")
	private Date createdDate;

	@Column(name = "issue_desc")
	private String issueDesc;

	@Column(name = "req_status")
	private String reqStatus;

	@Column(name = "repaired_datetime")
	private Date repairedDatetime;

	@ManyToOne
	@JoinColumn(name = "repaired_by")
	private CustomerEntity repairedBy;

	public int getReqNo() {
		return reqNo;
	}

	public void setReqNo(int reqNo) {
		this.reqNo = reqNo;
	}

	public BikeEntity getBike() {
		return bike;
	}

	public void setBike(BikeEntity bike) {
		this.bike = bike;
	}

	public CustomerEntity getReqBy() {
		return reqBy;
	}

	public void setReqBy(CustomerEntity reqBy) {
		this.reqBy = reqBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getIssueDesc() {
		return issueDesc;
	}

	public void setIssueDesc(String issueDesc) {
		this.issueDesc = issueDesc;
	}

	public String getReqStatus() {
		return reqStatus;
	}

	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}

	public Date getRepairedDatetime() {
		return repairedDatetime;
	}

	public void setRepairedDatetime(Date repairedDatetime) {
		this.repairedDatetime = repairedDatetime;
	}

	public CustomerEntity getRepairedBy() {
		return repairedBy;
	}

	public void setRepairedBy(CustomerEntity repairedBy) {
		this.repairedBy = repairedBy;
	}

	@Override
	public String toString() {
		return "{\"reqNo\":\"" + reqNo + "\", \"createdDate\":\"" + createdDate + "\", \"issueDesc\":\"" + issueDesc
				+ "\", \"reqStatus\":\"" + reqStatus + "\", \"repairedDatetime\":\"" + repairedDatetime + "\" }";
	}

	

}
