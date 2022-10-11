package com.zoho.carparking.controller;

import java.sql.SQLException;

public class CarParkingController {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		LoginController loginController = new LoginController();
		loginController.loginPage();

	}

}
