package main.HRManagement;

import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;

import employee.HRManagement.EmployeeMainMenu;
import hr.HRManagement.HRMainMenu;
import pm.HRManagement.PMMainMenu;

import pojo.HRPojo;

public class HRMSMain {

	static Scanner mainScreenInput = new Scanner(System.in);
	static String loginCheck = "Yes", role;
	static boolean returnValue;

	static HRPojo hrPojo = new HRPojo();

	public static void main(String args[]) throws SQLException, ParseException 
	{

		System.out.println("--------- Welcome To HR Management System ---------");		

		do 
		{
			System.out.println("Please Login  ");

			System.out.println("Enter Username - ");
			hrPojo.setUserName(mainScreenInput.nextLine());

			System.out.println("Enter Password - ");
			hrPojo.setPassword(mainScreenInput.nextLine());

			LoginTasks loginUser = new LoginTasks();
			returnValue = loginUser.loginCheck(hrPojo);

			if(returnValue == true)
			{
				role = hrPojo.getUserRole();
			}
			if (role != null && returnValue == true)
			{
				System.out.println("Welcome "+hrPojo.getUserFirstName());

				switch (role) 
				{
				case "HR":
					HRMainMenu hrMainMenu = new HRMainMenu();
					hrMainMenu.hrMainMenu(hrPojo);
					
					break;
				case "PM": // Project Manager
					PMMainMenu pmMainMenu = new PMMainMenu();
					pmMainMenu.pmMainMenu(hrPojo);
					break;
				case "Employee":
					EmployeeMainMenu employeeMainMenu = new EmployeeMainMenu();
					employeeMainMenu.employeeMainMenu(hrPojo);
					break;
				}
			}
		} while (loginCheck == "Yes");
		mainScreenInput.close();
		if(loginCheck != "Yes")
		{
			LoginTasks.updateLogoutDate(hrPojo);
		}
	}

}