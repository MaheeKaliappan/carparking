package com.zoho.carparking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zoho.carparking.model.Car;
import com.zoho.carparking.model.ParkingDetails;

public class CarParkingDAO {
	static Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;

	private void getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/car_parking?characterEncoding=latin1&useConfigs=maxPerformance", "root",
				"root");
	}
	public void addCarDetails(Car car) throws ClassNotFoundException, SQLException {
		getConnection();
		String sqlQuery = "INSERT INTO CAR (CAR_NUMBER,CAR_COMPANY_NAME,CAR_DRIVER_MOBILE_NUMBER) VALUES(?,?,?)";
		preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, car.getCarNo());
		preparedStatement.setString(2, car.getCarCompanyName());
		preparedStatement.setString(3, car.getCarDriverMobileNumber());
		
		preparedStatement.executeUpdate();
		
		
	}
	public Integer fetchCarId(Car car) throws SQLException, ClassNotFoundException {
		getConnection();
		Integer carId = null;
		String sqlQuery = "SELECT ID FROM CAR WHERE CAR_NUMBER= ?";
		preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, car.getCarNo());
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			carId = resultSet.getInt(1);

		}
		return carId;
	}
	public void addParkingDetails(Car car, ParkingDetails parkingDetails) throws ClassNotFoundException, SQLException {
		getConnection();
		String sqlQuery = "INSERT INTO parking_details (IN_TIME,DATE,PARKING_STATUS,CAR_ID) VALUES(?,?,?,?)";
		preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, parkingDetails.getInTime());
		preparedStatement.setString(2, parkingDetails.getDate());
		preparedStatement.setBoolean(3, parkingDetails.getStatus());
		preparedStatement.setInt(4, car.getCarId());
		
		
		preparedStatement.executeUpdate();
		
		
	}
	public Integer fetchOverAllRevenue() throws ClassNotFoundException, SQLException {
		getConnection();
		String sqlQuery = "SELECT sum(amount) FROM parking_details";
		preparedStatement = connection.prepareStatement(sqlQuery);
		Integer overAllRevenue = null;
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			 overAllRevenue = resultSet.getInt(1);

		}
		return overAllRevenue;
	}
	public Integer fetchCarInTime(Car car) throws ClassNotFoundException, SQLException {
		getConnection();
		String sqlQuery = "SELECT in_time FROM parking_details WHERE CAR_ID =?";
		preparedStatement = connection.prepareStatement(sqlQuery);
		Integer carInTime = null;
		preparedStatement.setInt(1, car.getCarId());
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			carInTime  = resultSet.getInt(1);

		}
		return carInTime;
		
	}
	public void updateParkingDetails(Car car, ParkingDetails parkingDetails) throws ClassNotFoundException, SQLException {
		getConnection();
		String sqlQuery = "UPDATE parking_details SET OUT_TIME =?,AMOUNT=?,PARKING_STATUS =? WHERE CAR_ID=?";
		preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, parkingDetails.getOutTime());
		preparedStatement.setInt(2, parkingDetails.getAmount());
		preparedStatement.setBoolean(3, parkingDetails.getStatus());
		preparedStatement.setInt(4, car.getCarId());
		

		preparedStatement.executeUpdate();

	}
	public int checkAvailPlaces() throws ClassNotFoundException, SQLException {
		getConnection();
		Integer availPlace = null;
		String sqlQuery ="select count(parking_status) from parking_details where parking_status = true";
		preparedStatement = connection.prepareStatement(sqlQuery);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			availPlace = resultSet.getInt(1);

		}
		return availPlace;
	}
	public List<Car> fetchAllActiveCarDetails() throws ClassNotFoundException, SQLException {
		getConnection();
		List<Car> activeCarList = new ArrayList<Car>(); 
		String sqlQuery ="select car.car_number,car.car_company_name,car.car_driver_mobile_number,\r\n"
				+ "                            parking_details.date,parking_details.in_time,parking_details.parking_status from car inner join parking_details on \r\n"
				+ "                            car.id= parking_details.car_id";
		
		preparedStatement = connection.prepareStatement(sqlQuery);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Car car = new Car();
			car.setCarNo(resultSet.getString(1));
			car.setCarCompanyName(resultSet.getString(2));
			car.setCarDriverMobileNumber(resultSet.getString(3));
			ParkingDetails parkingDetails = new ParkingDetails();
			parkingDetails.setDate(resultSet.getString(4));
			parkingDetails.setInTime(resultSet.getInt(5));
			parkingDetails.setStatus(resultSet.getBoolean(6));
			car.setParkingDetails(parkingDetails);
			activeCarList.add(car);
			

		}
		return activeCarList;
	}
	public int particularDateRevenue(String date) throws ClassNotFoundException, SQLException {
		Integer revenue = null;
		getConnection();
		String sqlQuery = "SELECT sum(amount) FROM parking_details WHERE DATE = ?";
		preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, date);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			revenue = resultSet.getInt(1);

		}
		return revenue;
	}

}
