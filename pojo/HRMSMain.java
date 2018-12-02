package pojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;

import employee.HRManagement.EmployeeMainMenu;
import hr.HRManagement.HRMainMenu;
import main.HRManagement.LoginTasks;
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

			hrPojo.setPassword(PasswordField.readPassword("Enter password :"));

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
class PasswordField {

	public static String readPassword (String prompt) {
		EraserThread et = new EraserThread(prompt);
		Thread mask = new Thread(et);
		mask.start();

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String password = "";

		try {
			password = in.readLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		et.stopMasking();
		return password;
	}
}   

class EraserThread implements Runnable {
	private boolean stop;

	public EraserThread(String prompt) {
		System.out.print(prompt);
	}

	public void run () {
		while (!stop){
			System.out.print("\010*");
			try {
				Thread.currentThread().sleep(1);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	public void stopMasking() {
		this.stop = true;
	}
}