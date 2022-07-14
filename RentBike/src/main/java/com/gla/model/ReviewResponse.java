package com.gla.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "review_response")
public class ReviewResponse implements Serializable {

	private static final long serialVersionUID = -1798070786993154676L;

	@Id
	@GeneratedValue
	@Column(name = "review_id")
	private int reviewId;

	@Column(name = "reviewed_by")
	private int reviewedBy;

	@Column(name = "booking_id")
	private int bookingId;

	@Column(name = "review_desc")
	private String reviewDesc;

	@Column(name = "no_of_stars")
	private int noOfStars;

	public ReviewResponse() {
		super();
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getReviewedBy() {
		return reviewedBy;
	}

	public void setReviewedBy(int reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getReviewDesc() {
		return reviewDesc;
	}

	public void setReviewDesc(String reviewDesc) {
		this.reviewDesc = reviewDesc;
	}

	public int getNoOfStars() {
		return noOfStars;
	}

	public void setNoOfStars(int noOfStars) {
		this.noOfStars = noOfStars;
	}

	@Override
	public String toString() {
		return "{\"reviewId\":\"" + reviewId + "\", \"reviewedBy\":\"" + reviewedBy + "\", \"bookingId\":\"" + bookingId
				+ "\", \"reviewDesc\":\"" + reviewDesc + "\", \"noOfStars\":\"" + noOfStars + "\" }";
	}
	
	
}
