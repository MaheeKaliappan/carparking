package com.zoho.carparking.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.zoho.carparking.dao.CarParkingDAO;
import com.zoho.carparking.model.Car;
import com.zoho.carparking.model.ParkingDetails;

public class CarController {
	CarParkingDAO carParkingDAO = new CarParkingDAO();
	LoginController loginController = new LoginController();
	 void addCarDetails() throws ClassNotFoundException, SQLException {
		Car car  = new Car();
		Scanner reader = new Scanner(System.in);
		int availPlaces = carParkingDAO.checkAvailPlaces();
		if(availPlaces < 20)
		{
			System.out.println("Enter car number");
			car.setCarNo(reader.next());
			System.out.println("Enter car company");
			car.setCarCompanyName(reader.next());
			System.out.println("Enter car driver contact number");
			car.setCarDriverMobileNumber(reader.next());
			carParkingDAO.addCarDetails(car);
			car.setCarId(carParkingDAO.fetchCarId(car));
			ParkingDetails parkingDetails = new ParkingDetails();
			System.out.println("Parking Date");
			parkingDetails.setDate(reader.next());
			System.out.println("Parking Time");
			parkingDetails.setInTime(reader.nextInt());
			parkingDetails.setStatus(true);
			carParkingDAO.addParkingDetails(car,parkingDetails);
			System.out.println("Car Datas Added Successfully");
			
		}
		else
		{
			System.out.println("Place Not Avail");
		}
		loginController.adminPage();
		
		
	}
	public void overAllRevenue() throws ClassNotFoundException, SQLException {
		
		Integer revenueAmout = carParkingDAO.fetchOverAllRevenue();
		if(revenueAmout == null)
		{
			System.out.println("Revenue Amout IS Null");
		}
		else
		{
			System.out.println("Over All Revenue Is : "+revenueAmout);
			loginController.adminPage();
		}
		
	}
	public void addOutTime() throws ClassNotFoundException, SQLException {
		Scanner reader = new Scanner(System.in);
		ParkingDetails parkingDetails = new ParkingDetails();
		Car car = new Car();
		System.out.println("Enter car Number");
		car.setCarNo(reader.next());
		car.setCarId(carParkingDAO.fetchCarId(car));
		System.out.println(car.getCarId());
		System.out.println("Enter out time");
		parkingDetails.setOutTime(reader.nextInt());
		Integer inTime =  carParkingDAO.fetchCarInTime(car);
		parkingDetails.setAmount(20*(parkingDetails.getOutTime()-inTime));
		parkingDetails.setStatus(false);
		carParkingDAO.updateParkingDetails(car,parkingDetails);	
		System.out.println("Parking Amount : "+parkingDetails.getAmount());
		loginController.adminPage();
	}
	public void viewParkingDetails() throws ClassNotFoundException, SQLException {
		
		List<Car>allActiveCarDetails =  carParkingDAO.fetchAllActiveCarDetails();
		if(allActiveCarDetails.size() == 0)
		{
			System.out.println("Active Car List Is Empty");
		}
		else
		{
			showActiveCarDetails(allActiveCarDetails);
		}
		loginController.adminPage();
		
	}
	private void showActiveCarDetails(List<Car> allActiveCarDetails) {
		System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s", "Car Number", "Car Company Name","Driver Contact Number","Date","In Time","Parking Status");
		System.out.println();
		for(int index = 0;index<allActiveCarDetails.size();index++)
		{
			System.out.println("____________________________________________________________________________________________________________________________________________-__________________________________________________________________________");
			System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s",allActiveCarDetails.get(index).getCarNo(),allActiveCarDetails.get(index).getCarCompanyName(),allActiveCarDetails.get(index).getCarDriverMobileNumber(),allActiveCarDetails.get(index).getParkingDetails().getDate(),allActiveCarDetails.get(index).getParkingDetails().getInTime(),allActiveCarDetails.get(index).getParkingDetails().getStatus());
		}
		System.out.println();
		
	}
	public void viewAvailPlaces() throws ClassNotFoundException, SQLException {
		Integer availPlaces = carParkingDAO.checkAvailPlaces();
		if(availPlaces == 0)
		{
			System.out.println("Avail Places : "+20);
		}
		else
		{
			System.out.println("Avail Places : "+(20-availPlaces));
		}
		loginController.adminPage();
		
		
	}
	public void viewParticularDateRevenue() throws ClassNotFoundException, SQLException {
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter date");
		String date = reader.next();
		Integer particularDateRevenue = carParkingDAO.particularDateRevenue(date);
		if(particularDateRevenue == 0)
		{
			System.out.println("Record Not Found");
		}
		else
		{
			System.out.println(date+" Revenue : "+particularDateRevenue);
		}
		loginController.adminPage();
		
		
	}


}
