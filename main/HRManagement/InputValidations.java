package main.HRManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import database.HRManagement.DatabaseConnection;
import pojo.HRPojo;

public class InputValidations {
	static int hours,minutes,date,month,year,todayDate,todayMonth,todayYear; 
	//Database connection
	private static DatabaseConnection dbConnector = new DatabaseConnection();
	private static Statement statement = null;
	public static boolean TimeValidation(String trainingTime) {

		if(trainingTime.length() == 5)
		{
			String[] trainingTimeArray = trainingTime.split(":");

			hours = Integer.parseInt(trainingTimeArray[0]);
			minutes = Integer.parseInt(trainingTimeArray[1]);

			if(hours > 24 || minutes > 60)
			{	
				System.out.println("Please enter valid time");
				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			System.out.println("Please enter valid time");
			return false;
		}

	}

	public static boolean DateValidation(String trainingDate){
		try{
			LocalDate localDate = LocalDate.now();

			final String INPUT_FORMAT = "dd/MM/yyyy";
			final String OUTPUT_FORMAT = "yyyy/MM/dd";

			SimpleDateFormat sdf = new SimpleDateFormat(INPUT_FORMAT);
			Date d;
			try {
				d = sdf.parse(trainingDate);
				sdf.applyPattern(OUTPUT_FORMAT);
				trainingDate = sdf.format(d);
			} 
			catch (ParseException e) 
			{
				System.out.println("Enter valid date");
				return false;
			}

			String todaysDate = DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate);

			if(todaysDate.compareTo(trainingDate) < 0)
			{
				System.out.println("Please enter valid date");
				return false;
			}
			else
			{
				return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public static String emailCheck(String emailId,HRPojo hrPojo) throws SQLException {
		boolean emailValid = true;
		int count = 0;
		statement = dbConnector.getConnection().createStatement();
		while (emailValid == true)
		{
			ResultSet emailCheck = statement.executeQuery(
					"SELECT Email_id FROM employee_master where Email_id = '"+emailId+"'");
			if(emailCheck.next())
			{
				count = count + 1;
				emailId = hrPojo.getEmployeeFirstName()+"."+hrPojo.getEmployeeLastName()+count+"@hrms.com";
			}
			else
			{
				emailValid = false;
			}
		}
		if( count  > 0)
		{
			return emailId;
		}
		else
		{
			emailId = "";
			return emailId;
			}
	}

}
