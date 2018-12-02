package main.HRManagement;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import database.HRManagement.DatabaseConnection;

import pojo.HRPojo;

public class LoginTasks {

	private static Statement statement = null;
	private boolean returnValue;
	private static DatabaseConnection dbConnector = new DatabaseConnection();
	static Scanner loginInput = new Scanner(System.in);
	String status;	
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	public boolean loginCheck(HRPojo hrPojo) {
		try 
		{
			hrPojo.setUserRole(" ");
			hrPojo.setUserDesignation(" ");
			hrPojo.setUserFirstName(" ");
			hrPojo.setUserSupervisor(" ");
			returnValue = false;
			statement = dbConnector.getConnection().createStatement();

			ResultSet loginValues = statement.executeQuery(
					"SELECT * FROM employee_master where Employee_id = '"+hrPojo.getUserName()+"' and password = '"+hrPojo.getPassword()+"'" );

			if(loginValues.next())
			{
				hrPojo.setUserRole(loginValues.getString("Role"));
				hrPojo.setUserDesignation(loginValues.getString("Designation"));
				hrPojo.setUserFirstName(loginValues.getString("First_name"));
				hrPojo.setUserSupervisor(loginValues.getString("Supervisor"));

				updateLoginData(hrPojo);				
				status = loginValues.getString("Status");
				returnValue = true;
			}
			else
			{
				returnValue = false;
				System.out.println("Enter Valid credentials");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		if(status == "Inactive" && returnValue == true)
		{
			returnValue = false;
			System.out.println("Employee is disabled");
		}
		return returnValue;
	}

	private void updateLoginData(HRPojo hrPojo) throws SQLException {
		
		LocalDateTime localDate = LocalDateTime.now();
		String todaysDate = dtf.format(localDate);
		statement = dbConnector.getConnection().createStatement();
		ResultSet loginData = statement.executeQuery(
				"SELECT max(id) as id FROM login_history ");
		if(loginData.next())
		{
			int id = loginData.getInt("id");
			id = id + 1;
			hrPojo.setId(id);
			String Update_Login_info = "INSERT INTO login_history(id,Employee_id,Login_Timestamp) "
					+ "values('"+hrPojo.getId()+"','"+hrPojo.getUserName()+"','"+todaysDate+"') ";

			java.sql.PreparedStatement preparedStmt = dbConnector.getConnection().prepareStatement(Update_Login_info);
			preparedStmt.execute();
		}
	}

	public static void updateLogoutDate(HRPojo hrPojo) throws SQLException {
		LocalDateTime localDate = LocalDateTime.now();
		String todaysDate = dtf.format(localDate);
			String Update_Logout_info = "UPDATE login_history set Logout_Timestamp = '"+todaysDate+"'"
					+ " where id = '"+hrPojo.getId()+"'";

			java.sql.PreparedStatement preparedStmt = dbConnector.getConnection().prepareStatement(Update_Logout_info);
			preparedStmt.execute();
	}
}
