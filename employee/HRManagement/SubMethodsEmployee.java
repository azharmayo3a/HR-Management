package employee.HRManagement;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

import database.HRManagement.DatabaseConnection;
import pojo.HRPojo;

public class SubMethodsEmployee 
{
	private static Statement statement = null;

	private static DatabaseConnection dbConnector = new DatabaseConnection();
	static int deleteAddress, skillSetLength,existingLeaves,requestNo,check;
	static String primaryAddress,skillSet, skillSetNew;
	static char primaryCheck;
	static long noOfDays,updatedLeave;

	//	static hrPojo hrPojo = new hrPojo();
	static HRPojo hrPojo = new HRPojo();

	static Scanner SubMethodsEmployeeInput = new Scanner(System.in);


	public static void viewSkillset(HRPojo hrPojo) 
	{
		System.out.println("|---Skillsets of "+hrPojo.getUserName()+"---|");
		try 
		{
			statement = dbConnector.getConnection().createStatement();
			ResultSet viewSkillset = statement.executeQuery(
					"SELECT * FROM employee_master where Employee_id = '"+hrPojo.getUserName()+"'");
			System.out.println("====================================\n");
			if(viewSkillset.next())
			{
				skillSet = viewSkillset.getString("Skillset");

				String[] skillSetDetailed = skillSet.split(",");
				skillSetLength = skillSetDetailed.length;
				for(int i=0;i<skillSetLength;i++)
				{
					System.out.println(skillSetDetailed[i]+"\n");
				}
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("====================================\n");
	}

	public static void addSkillset(HRPojo hrPojo) 
	{
		viewSkillset(hrPojo);
		System.out.println("|---Add Skillset to an Employee----|");

		try 
		{
			System.out.println("Metion the Skillsets you would like to add(If multiple seperate it by ',') : ");
			skillSetNew = SubMethodsEmployeeInput.nextLine();
			skillSetNew = skillSetNew.toUpperCase();
			skillSet = skillSet + ',' + skillSetNew;
			String query = "UPDATE employee_master set Skillset = '"+skillSet+"' "
					+ "where Employee_id = '"+hrPojo.getUserName()+"' ";
			java.sql.PreparedStatement preparedStmt = dbConnector.getConnection().prepareStatement(query);
			preparedStmt.execute();
			System.out.println("Skillsets "+skillSetNew+" is Added successfully");
			System.out.println("Current Skillsets are "+skillSet+".");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public static void editSkillset(HRPojo hrPojo) 
	{
		viewSkillset(hrPojo);
		System.out.println("|---Edit Skillset to an Employee----|");
		try 
		{
			System.out.println("Re enter all the skillsets correctly seperated it by ',' - ");
			skillSetNew = SubMethodsEmployeeInput.nextLine();
			skillSetNew = skillSetNew.toUpperCase();
			String query = "UPDATE employee_master set Skillset = '"+skillSetNew+"' "
					+ "where Employee_id = '"+hrPojo.getUserName()+"' ";
			java.sql.PreparedStatement preparedStmt = dbConnector.getConnection().prepareStatement(query);
			preparedStmt.execute();
			System.out.println("Skillsets "+skillSetNew+" updated successfully");
			System.out.println("Current Skillsets are "+skillSetNew+".");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public static void viewProfile(HRPojo hrPojo) {
		try {
			statement = dbConnector.getConnection() . createStatement();
			ResultSet employeeDetails = statement.executeQuery("SELECT * FROM employee_master where Employee_id = '"+hrPojo.getUserName()+"'");
			System.out.println("==================\n");
			while (employeeDetails.next())
			{
				System.out.println("Employee ID - "+ employeeDetails.getInt("Employee_id"));
				System.out.println("FirstName - "+ employeeDetails.getString("First_name"));
				System.out.println("LastName - "+ employeeDetails.getString("Last_name"));
				System.out.println("Date_Of_Birth - "+ employeeDetails.getString("Date_Of_Birth"));
				System.out.println("Phonenumber - "+ employeeDetails.getString("Phone_number"));
				System.out.println("Gender - "+ employeeDetails.getString("Gender"));
				System.out.println("Designation - "+ employeeDetails.getString("Designation"));
				System.out.println("Role - "+ employeeDetails.getString("Role"));
				System.out.println("--------------------\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	public static void applyLeave(HRPojo hrPojo) 
	{
		System.out.println("|---Apply Leave----|"+hrPojo.getUserName());

		String typeCheck = "invalid",leave = "",fromDate,toDate,description;
		long phoneNumber;
		do
		{
			while(typeCheck == "invalid")
			{
				System.out.println("Type of Leave :"
						+ "\n1. Medical,"
						+ "\n2. Casual,"
						+ "\n3. Earn Leave");
				int leaveType= Integer.parseInt(SubMethodsEmployeeInput.nextLine());
				switch (leaveType) 
				{

				case 1:
					leave="MedicalLeave";
					typeCheck = "valid";
					break;
				case 2:
					typeCheck = "valid";
					leave="CasualLeave";
					break;
				case 3:
					typeCheck = "valid";
					leave="EarnLeave";
					break;
				default:
					typeCheck = "invalid";
					System.out.println("Please enter valid input");
					break;
				}
			}

			System.out.println("Date From (yyyy-mm-dd)");
			fromDate=SubMethodsEmployeeInput.nextLine();
			System.out.println("Date To (yyyy-mm-dd)");
			toDate=SubMethodsEmployeeInput.nextLine();   
			System.out.println("Contact while on leave");
			phoneNumber=Long.parseLong(SubMethodsEmployeeInput.nextLine());
			System.out.println("Reason");
			description=SubMethodsEmployeeInput.nextLine();

			SimpleDateFormat formate=new SimpleDateFormat("yyyy-mm-dd")	;
			try 
			{
				Date d1 = formate.parse(fromDate);

				Date tdate=(Date) formate.parse(toDate);

				long day=Math.abs(d1.getTime()-tdate.getTime());
				noOfDays=day/(24 * 60 * 60 * 1000);
				noOfDays = noOfDays + 1;
				statement = dbConnector.getConnection() . createStatement();
				ResultSet employeeDetails = statement.executeQuery("SELECT * FROM leave_master where Employee_id = '"+hrPojo.getUserName()+"'");

				if(employeeDetails.next())
				{
					existingLeaves = employeeDetails.getInt(leave);
				}

				updatedLeave = existingLeaves - noOfDays;

				if(updatedLeave < 0)
				{
					System.out.println("Leave balance is not sufficient. \nYour Leave Balance  of this type is " +existingLeaves);
					typeCheck = "invalid";
				}
				else
				{
					String query="UPDATE leave_master SET "+leave+" = '"+updatedLeave+"' WHERE Employee_id='"+hrPojo.getUserName()+"'";			
					java.sql.PreparedStatement preparedStmt = dbConnector.getConnection().prepareStatement(query);
					boolean result = preparedStmt.execute();
					if(result == true)
						typeCheck = "exit";
				}
			}
			catch (Exception e) 
			{
				System.out.println(e.getMessage()+"jj");

			}
		}
		while(typeCheck == "invalid");
		System.out.println("--------Your Request Detail is--------\n");

		System.out.println("Leave Type- "+leave
				+ "\n Date from "+fromDate
				+ "\n Date to "+toDate
				+ "\n No of Days "+noOfDays
				+ "\n Contact Number "+phoneNumber
				+ "\n Description "+description);
		System.out.println("--------------------\n");
		System.out.println("Do you wish to apply leave with the above details ? (Y/N)");
		char applyLeave= SubMethodsEmployeeInput.next().charAt(0);
		if(applyLeave=='Y'||applyLeave=='y')
		{
			try 
			{
				String query="INSERT INTO leaves(Employee_id,LeaveType,FromDate,Todate,NoDays,ContactNo,reason,Status,Supervisor) "
						+ "values('"+hrPojo.getUserName()+"','"+leave+"','"+fromDate+"','"+toDate+"','"+noOfDays+"','"+phoneNumber+"',"
						+ "'"+description+"','Approval Pending','"+hrPojo.getUserSupervisor()+"')";			
				java.sql.PreparedStatement preparedStmt = dbConnector.getConnection().prepareStatement(query);
				preparedStmt.execute();
				System.out.println("Leave from "+fromDate+" till "+ toDate+" is pending for approval with your supervisor(Employee ID) - "+hrPojo.getUserSupervisor());
			}
			catch (SQLException e) 
			{
				System.out.println(e.getMessage());
			}
		}
	}


	public static void viewLeaveRequests(HRPojo hrPojo) {
		System.out.println("|---All the leave requests----|");

		try
		{
			statement = dbConnector.getConnection().createStatement();
			ResultSet pendingLeaveRequests = statement.executeQuery(
					"SELECT * FROM Leaves where Employee_id = '"+hrPojo.getUserName()+"'");
			System.out.println("==================================");
			while(pendingLeaveRequests.next())
			{ 
				requestNo = requestNo + 1;
				System.out.println("Request ID - "+ pendingLeaveRequests.getString("LeaveId"));
				System.out.println("Type of leave -"+ pendingLeaveRequests.getString("Leavetype"));
				System.out.println("From Date- "+ pendingLeaveRequests.getString("FromDate"));
				System.out.println("To Date - "+ pendingLeaveRequests.getString("Todate"));
				System.out.println("No of days -"+ pendingLeaveRequests.getString("NoDays"));
				System.out.println("Reason -"+ pendingLeaveRequests.getString("reason"));
				System.out.println("Supervisor -"+ pendingLeaveRequests.getString("Supervisor"));
				System.out.println("Status- "+ pendingLeaveRequests.getString("Status"));
				System.out.println("--------------------\n");
			}
			if(requestNo == 0)
			{
				System.out.println("No Requests pending for action");
			}
			else
			{
				System.out.println(requestNo +" requests are pending for approval");
			}
			System.out.println("==================================");
		}

		catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void viewLeaveBalance(HRPojo hrPojo) {
		try {

			statement = dbConnector.getConnection().createStatement();
			ResultSet checkBlance = statement.executeQuery("SELECT * from leave_master where Employee_id='"+hrPojo.getUserName()+"'");
			System.out.println("==================\n");
			System.out.println("Leave Balance");
			System.out.println("--------------\n");
			while(checkBlance.next())
			{
				System.out.println("Earn Leaves -"+checkBlance.getInt("EarnLeave"));
				System.out.println("Medical Leaves - "+checkBlance.getInt("MedicalLeave"));
				System.out.println("Casual Leaves - "+checkBlance.getInt("CasualLeave"));
			}	
			System.out.println("==================\n");
		}
		catch (SQLException e) {
			System.out.println("Error in fetching data");
			System.out.println(e.getMessage());;
		}
	}

	public static void cancelLeaveRequest(HRPojo hrPojo) {
		int leave_request = 0;
		String type = null;
		System.out.println("Enter the leave request to cancel");
		check = 0;
		while(check == 0)
		{
			try 
			{
				leave_request = Integer.parseInt(SubMethodsEmployeeInput.nextLine());
				check = 1;
			}
			catch (NumberFormatException e) 
			{
				check = 0;
				System.out.println("Provide valid leave request");
			}
		}
		try {
			statement = dbConnector.getConnection().createStatement();
			ResultSet validateLeaveRequest = statement.executeQuery("SELECT * from leaves where Employee_id='"+hrPojo.getUserName()+"' and LeaveId = '"+leave_request+"'");
			check = 0;
			while(validateLeaveRequest.next()){
				type = validateLeaveRequest.getString("Leavetype");
				noOfDays = validateLeaveRequest.getInt("NoDays");
				++check;
			}
			if(check > 0)
			{
				
				String deleteLeaveRequest ="DELETE FROM leaves WHERE Employee_id='"+hrPojo.getUserName()+"' and LeaveId = '"+leave_request+"'";			
				java.sql.PreparedStatement preparedStmt = dbConnector.getConnection().prepareStatement(deleteLeaveRequest);
				boolean result = preparedStmt.execute();
				if(result == false)
				{
					updateLeaveBalance(type,noOfDays,hrPojo);
					System.out.println("Leave request "+leave_request+" cancelled successfully");
				}
				else
				{
					System.out.println("Error in cancelling the request."+ leave_request+" \nKindly check leave request and proceed again" );	
				}
			}
			else
			{
				System.out.println("Leave request "+ leave_request+" is not valid. Try again with the valid request" );
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public static void updateLeaveBalance(String type, long noOfDays, HRPojo hrPojo) {
		try {
		String updateLeaveRequest ="Update leave_master set "+type+" = "+type+"+"+noOfDays+" where Employee_id = '"+hrPojo.getUserName()+"'";			
		PreparedStatement preparedStmt = dbConnector.getConnection().prepareStatement(updateLeaveRequest);
		preparedStmt.execute();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void addAddress(HRPojo hrPojo) {
		
		System.out.println("|---Provide Room No/House No/Flat No----|");
		
		hrPojo.setFlatNo(SubMethodsEmployeeInput.nextLine());	
		System.out.println("|---Provide the street name----|");
		hrPojo.setStreetName(SubMethodsEmployeeInput.nextLine());
		System.out.println("|---Pincode of the area----|");
		check = 0;
		while(check == 0)
		{
			try 
			{
				hrPojo.setPinCode(Integer.parseInt(SubMethodsEmployeeInput.nextLine()));
				check = 1;
			}
			catch (NumberFormatException e) 
			{
				System.out.println("provide valid pincode");
				check = 0;
			}
		}
		System.out.println("|---Provide the city----|");
		hrPojo.setCity(SubMethodsEmployeeInput.nextLine());
		System.out.println("|-Do you want to make this as a primary address ? (Y/N)\n -|");
		primaryCheck = SubMethodsEmployeeInput.nextLine().charAt(0);

		try 
		{
			if(primaryCheck == 'Y' || primaryCheck == 'y')
			{
				String updatePrimaryAddress = "UPDATE address set PrimaryAddress = ' ' "
						+ "where Employee_id = '"+hrPojo.getUserName()+"' and PrimaryAddress = 'X'";
				java.sql.PreparedStatement preparedStmt = dbConnector.getConnection().prepareStatement(updatePrimaryAddress);
				preparedStmt.execute();
				preparedStmt.close();
				primaryCheck = 'X';
			}
			else
			{
				primaryCheck = ' ';
			}
			String addAddress = "INSERT INTO address(Employee_id,FlatNo,StreetName,Pincode,City,PrimaryAddress) "
					+ "values('"+hrPojo.getUserName()+"','"+hrPojo.getFlatNo()+"','"+hrPojo.getStreetName()+"',"
					+ "'"+hrPojo.getPinCode()+"','"+hrPojo.getCity()+"','"+hrPojo.getPrimaryCheck()+"') ";
			java.sql.PreparedStatement preparedStmt = dbConnector.getConnection().prepareStatement(addAddress);
			preparedStmt.execute();
			System.out.println("Address is added successfully");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}
	public static void viewAddress(HRPojo hrPojo) {
		check = 0;
		try {
			statement = dbConnector.getConnection().createStatement();
			ResultSet addressList = statement.executeQuery("SELECT * from address where Employee_id='"+hrPojo.getUserName()+"'");
			System.out.println("==================\n");
			while(addressList.next())
			{
				System.out.println("Address Id :"+addressList.getInt("AddressId"));
				System.out.println("Street Name:"+addressList.getString("StreetName"));
				System.out.println("Flat No :"+addressList.getString("FlatNo"));
				System.out.println("Pincode :"+addressList.getInt("Pincode"));
				System.out.println("City :"+addressList.getString("City"));
				if(addressList.getString("PrimaryAddress").isEmpty())
				{
					System.out.println("Primary Address : No");
				}
				else
				{
					System.out.println("Primary Address : Yes");
				}
				++check ;
				System.out.println("-------------------\n");
			}	
			
		}
		catch (SQLException e) {
			System.out.println("Address is not available. Kindly update");
			e.printStackTrace();
		}
		if(check == 0)
		{
			System.out.println("No address is maintained");
		}
		System.out.println("==================\n");
	}

	public static void deleteAddress(HRPojo hrPojo) {
		viewAddress(hrPojo);
		if(check != 0)
		{
			do
			{
				System.out.println("|--Provide the address id to delete--|");
				hrPojo.setAddressId(Integer.parseInt(SubMethodsEmployeeInput.nextLine()));
				try {
					statement = dbConnector.getConnection() . createStatement();
					ResultSet employeeDetails = statement.executeQuery("SELECT * FROM address WHERE AddressId = '"+hrPojo.getAddressId()+"'");
					if(employeeDetails.next())
					{
						String query = "DELETE FROM address WHERE AddressId = '"+hrPojo.getAddressId()+"' AND Employee_id = '"+hrPojo.getUserName()+"'";
						java.sql.PreparedStatement preparedStmt = dbConnector.getConnection().prepareStatement(query);
						preparedStmt.execute();
						System.out.println("Deleted Successfully");
						deleteAddress = 0; //Exit loop
					}
					else
					{
						deleteAddress = 1;
						System.out.println("Provide correct address ID");
					}

				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			while(deleteAddress == 1);
		}
	}

	public static void changePassword(HRPojo hrPojo) throws SQLException {
		System.out.println("Enter new password - ");
		String newPassword = SubMethodsEmployeeInput.nextLine();
		System.out.println("Re-Enter new password - ");
		String newPasswordConfirm = SubMethodsEmployeeInput.nextLine();
		if(newPassword.equals(newPasswordConfirm))
		{

			String updatePassword = "UPDATE employee_master set Password = '"+newPassword+"' where Employee_id = '"+hrPojo.getUserName()+"' ";
			java.sql.PreparedStatement preparedStmt = dbConnector.getConnection().prepareStatement(updatePassword);
			preparedStmt.execute();
			System.out.println("Password changed successfully");
		}
		else
		{
			System.out.println("Passwords doesnot match");
			changePassword(hrPojo);
		}
	}
}