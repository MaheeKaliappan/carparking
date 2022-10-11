package com.zoho.carparking.model;

public class ParkingDetails {

	private Integer inTime;
	private Integer outTime;
	private String date;
	private Integer amount;
	private Boolean status;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getInTime() {
		return inTime;
	}
	public void setInTime(Integer inTime) {
		this.inTime = inTime;
	}
	public Integer getOutTime() {
		return outTime;
	}
	public void setOutTime(Integer outTime) {
		this.outTime = outTime;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
