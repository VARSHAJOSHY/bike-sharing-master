package com.gla.model;

public class ReportDTO {

	private Integer stationId;
	private String stationName;
	private Integer bikeId;
	private String bikeNo;
	private Integer noOfBikes;
	private String month;
	private String profit;
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Integer getBikeId() {
		return bikeId;
	}
	public void setBikeId(Integer bikeId) {
		this.bikeId = bikeId;
	}
	public String getBikeNo() {
		return bikeNo;
	}
	public void setBikeNo(String bikeNo) {
		this.bikeNo = bikeNo;
	}
	public Integer getNoOfBikes() {
		return noOfBikes;
	}
	public void setNoOfBikes(Integer noOfBikes) {
		this.noOfBikes = noOfBikes;
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	
}
