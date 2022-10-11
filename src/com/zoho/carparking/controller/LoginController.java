package com.zoho.carparking.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.zoho.carparking.model.Car;

public class LoginController {

	public void loginPage() throws ClassNotFoundException, SQLException {
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter Admin Name");
		String adminName = reader.next();
		System.out.println("Enter password");
		String password = reader.next();
		
		if(adminName.equalsIgnoreCase("admin")&&password.equals("1234"))
		{
			adminPage();
		}
		else
		{
			System.out.println("MisMatch Admin Name & Password");
			loginPage();
		}
		
	}

	 void adminPage() throws ClassNotFoundException, SQLException {
		Scanner reader = new Scanner(System.in);
		System.out.println("1.Add Car Details\n2.Add Out Time\n3.View Parking Details\n4.View Avail Places\n5.View Particular Date Revenue\n6.Over All Revenue\n7.Logout");
		int userChoice = reader.nextInt();
		CarController carController = new CarController();
		switch (userChoice) {
		case 1:
			carController.addCarDetails();
			break;
		case 2:
			carController.addOutTime();
			break;
		case 3:
			carController.viewParkingDetails();
			break;
		case 4:
			carController.viewAvailPlaces();
			break;
		case 5:
			carController.viewParticularDateRevenue();
			break;
		case 6:
			carController.overAllRevenue();
			break;
		case 7:
			System.out.println("THANKS FOR VISIT");
			loginPage();
			break;

		default:
			System.out.println("Mismatch Input");
			break;
		}
	}


}
