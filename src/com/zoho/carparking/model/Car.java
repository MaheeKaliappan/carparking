package com.zoho.carparking.model;

public class Car {
	private Integer carId;
	
	private String  carNo;
	
	private String  carCompanyName;
	
	private String carDriverMobileNumber;
	
	private ParkingDetails parkingDetails;
	
	public ParkingDetails getParkingDetails() {
		return parkingDetails;
	}

	public void setParkingDetails(ParkingDetails parkingDetails) {
		this.parkingDetails = parkingDetails;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getCarCompanyName() {
		return carCompanyName;
	}

	public void setCarCompanyName(String carCompanyName) {
		this.carCompanyName = carCompanyName;
	}

	public String getCarDriverMobileNumber() {
		return carDriverMobileNumber;
	}

	public void setCarDriverMobileNumber(String carDriverMobileNumber) {
		this.carDriverMobileNumber = carDriverMobileNumber;
	}
	
	
	
	

}
