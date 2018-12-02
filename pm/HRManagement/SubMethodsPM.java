package pm.HRManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;

import database.HRManagement.DatabaseConnection;
import employee.HRManagement.SubMethodsEmployee;
import pojo.HRPojo;

public class SubMethodsPM 
{


	private static DatabaseConnection dbConnector = new DatabaseConnection();

	static Scanner subMethodsPMInput = new Scanner(System.in);

	static int requestNo, noOfRequests, employeeCount, noOfDays;
	
	static String type;

	static Timestamp approvalDate;



	public static void pendingRequestsTraining(HRPojo hrPojo) 
	{

		System.out.println("|---Pending Requests of Training----|");
		try 
		{
			statement = dbConnector.getConnection().createStatement();
			ResultSet pendingTrainingRequests = statement.executeQuery(
					"SELECT * FROM Trainings_registered where Supervisor = '"+hrPojo.getUserName()+"' And Status = 'Approval Pending'");
			System.out.println("====================================\n");
			while (pendingTrainingRequests.next())
			{
				noOfRequests = noOfRequests + 1;  
				System.out.println("Request ID - "+ pendingTrainingRequests.getString("Request_id"));
				System.out.println("Employee ID - "+ pendingTrainingRequests.getString("Employee_id"));
				System.out.println("Training ID- "+ pendingTrainingRequests.getString("Training_id"));
				System.out.println("--------------------\n");

			}
			if(noOfRequests == 0)
			{
				System.out.println("No Requests pending for action");
			}
			else
			{
				System.out.println(noOfRequests+" requests are pending.");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		System.out.println("====================================\n");
	}

	public static void approvePendingRequestsTraining(HRPojo hrPojo) 
	{
		pendingRequestsTraining(hrPojo);
		if (noOfRequests != 0)
		{
			System.out.println("Which Request ID should get Approval : ");
			requestNo = subMethodsPMInput.nextInt();
			try {
				Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
				approvalDate = timeStamp;
				String query = "UPDATE trainings_registered set Status = 'Approved',Action_Date = '"+approvalDate+"' "
						+ "where Request_id = '"+requestNo+"' ";
				java.sql.PreparedStatement preparedStmt = dbConnector.getConnection().prepareStatement(query);
				preparedStmt.execute();
				System.out.println("Request No "+requestNo+" is approved");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void rejectPendingRequestsTraining(HRPojo hrPojo) 
	{
		pendingRequestsTraining(hrPojo);
		if (noOfRequests != 0)
		{
			System.out.println("Which Request ID should Reject : ");
			requestNo = subMethodsPMInput.nextInt();
			try {
				Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
				approvalDate = timeStamp;
				String query = "UPDATE trainings_registered set Status = 'Rejected',Action_Date = '"+approvalDate+"' "
						+ "where Request_id = '"+requestNo+"' ";
				java.sql.PreparedStatement preparedStmt = dbConnector.getConnection().prepareStatement(query);
				preparedStmt.execute();
				System.out.println("Request No "+requestNo+" is rejected");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void viewEmployeesInProject(HRPojo hrPojo) 
	{
		employeeCount = 0;
		System.out.println("|---All Employees under you ----|");
		try 
		{
			statement = dbConnector.getConnection().createStatement();
			ResultSet employeesList = statement.executeQuery(
					"SELECT * FROM project_employee where Supervisor = '"+hrPojo.getUserName()+"'");
			System.out.println("====================================\n");
			while (employeesList.next())
			{
				employeeCount = employeeCount + 1;
				System.out.println("Employee ID - "+ employeesList.getString("Employee_id"));
				System.out.println("Project Role- "+ employeesList.getString("project_role"));
				System.out.println("--------------------\n");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		if(employeeCount != 0)
		{
			System.out.println(employeeCount+" assosiates are tagged under you\n");
		}
		else
		{
			System.out.println("No employee is  tagged under you\n");
		}
		System.out.println("====================================\n");		
	}
	public static void pendingRequestsLeave(HRPojo hrPojo) 
	{
		noOfRequests = 0;
		System.out.println("|---Pending Requests of Leave----|");
		try 
		{
			statement = dbConnector.getConnection().createStatement();
			ResultSet pendingTrainingRequests = statement.executeQuery(
					"SELECT * FROM Leaves where Supervisor = '"+hrPojo.getUserName()+"' And Status = 'Approval Pending'");
			System.out.println("====================================\n");
			while (pendingTrainingRequests.next())
			{
				noOfRequests = noOfRequests + 1;  
				System.out.println("Request ID - "+ pendingTrainingRequests.getString("LeaveId"));
				System.out.println("Employee ID - "+ pendingTrainingRequests.getString("Employee_id"));
				System.out.println("Leave Type- "+ pendingTrainingRequests.getString("Leavetype"));
				System.out.println("From- "+ pendingTrainingRequests.getString("FromDate"));
				System.out.println("Till- "+ pendingTrainingRequests.getString("ToDate"));
				System.out.println("Reason- "+ pendingTrainingRequests.getString("Reason"));
				System.out.println("--------------------\n");

			}
			if(noOfRequests == 0)
			{
				System.out.println("No Requests pending for action");
			}
			else
			{
				System.out.println(noOfRequests+" requests are pending.");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		System.out.println("====================================\n");
	}

	public static void approveLeave(HRPojo hrPojo) {
		pendingRequestsLeave(hrPojo);
		if (noOfRequests != 0)
		{
			System.out.println("Which Request ID should get Approval : ");
			requestNo = subMethodsPMInput.nextInt();
			try {
				Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
				approvalDate = timeStamp;
				String query = "UPDATE leaves set Status = 'Approved',Approve_Date = '"+approvalDate+"' "
						+ "where LeaveId = '"+requestNo+"' ";
				java.sql.PreparedStatement preparedStmt = dbConnector.getConnection().prepareStatement(query);
				preparedStmt.execute();
				System.out.println("Request No "+requestNo+" is approved");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void rejectLeave(HRPojo hrPojo) {
		pendingRequestsLeave(hrPojo);
		if (noOfRequests != 0)
		{
			System.out.println("Which Request ID should Reject : ");
			requestNo = subMethodsPMInput.nextInt();
			try {
				Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
				approvalDate = timeStamp;
				String query = "UPDATE leaves set Status = 'Rejected',Approve_Date = '"+approvalDate+"' "
						+ "where LeaveId = '"+requestNo+"' ";
				java.sql.PreparedStatement preparedStmt = dbConnector.getConnection().prepareStatement(query);
				preparedStmt.execute();
				statement = dbConnector.getConnection().createStatement();
				ResultSet pendingTrainingRequests = statement.executeQuery(
						"SELECT * FROM Leaves where LeaveId = '"+requestNo+"'");
				if(pendingTrainingRequests.next())
				{
					type = pendingTrainingRequests.getString("LeaveType"); 
					noOfDays = pendingTrainingRequests.getInt("NoDays"); 
				}
				SubMethodsEmployee.updateLeaveBalance(type,noOfDays,hrPojo);
				System.out.println("Request No "+requestNo+" is rejected");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}