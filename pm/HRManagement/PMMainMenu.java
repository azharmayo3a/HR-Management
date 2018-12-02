package pm.HRManagement;

import java.sql.SQLException;
import java.util.Scanner;

import hr.HRManagement.SubMethodsHR;
import main.HRManagement.LoginTasks;
import pojo.HRPojo;

public class PMMainMenu 
{
	private int optionPM;
	Scanner pmScreenInput = new Scanner(System.in);
	public String loginCheck;
	String pmNavigationCheck = "Main";

	public void pmMainMenu(HRPojo hrPojo) throws SQLException 
	{
		do 
		{
			System.out.println("Choose any option to perform : " + "\n"
					+ "1. Trainings" + "\n"
					+ "2. leave " + "\n"
					+ "3. List of employees in my project" + "\n"
					+ "4. Logout");
			try 
			{
				optionPM = Integer.parseInt(pmScreenInput.nextLine());
			}
			catch (NumberFormatException e) 
			{
				//System.out.println(e);
			}
			switch (optionPM) {
			case 1:
				pmTrainingMenu(hrPojo);
				break;
			case 2:
				
				pmLeaveMenu(hrPojo);
				break;
			case 3:
				SubMethodsPM.viewEmployeesInProject(hrPojo);
				break;
			case 4:
				loginCheck = "No";
				pmNavigationCheck = "Payroll";
				System.out.println("Thank You for using the application.....");
				LoginTasks.updateLogoutDate(hrPojo);
				break;
			default:
				System.out.println("Please enter valid input");
				break;
			}
		}
		while (pmNavigationCheck == "Main");
	}

	private void pmTrainingMenu(HRPojo hrPojo) throws SQLException 
	{
		int trainingOption = 0;
		pmNavigationCheck = "trainingApproval";
		do 
		{
			System.out.println("Choose an Employee action to perform : "
					+ "\n1. List of requests pending for approval" + "\n"
					+ "2. Approve Request"+ "\n"
					+ "3. Reject Request" + "\n"
//					+ "4. Enroll for Training" + "\n"
					+ "4. View Trainings"+ "\n"
					+ "5. Back" + "\n"
					+ "6. Logout");
			try 
			{
				trainingOption = Integer.parseInt(pmScreenInput.nextLine());
			}
			catch (NumberFormatException e) 
			{
				//System.out.println(e);
			}
			switch (trainingOption) 
			{
			case 1:
				pmNavigationCheck = "trainingApproval";
				SubMethodsPM.pendingRequestsTraining(hrPojo);
				break;
			case 2:
				pmNavigationCheck = "trainingApproval";
				SubMethodsPM.approvePendingRequestsTraining(hrPojo);
				break;
			case 3:
				pmNavigationCheck = "trainingApproval";
				SubMethodsPM.rejectPendingRequestsTraining(hrPojo);
				break;
//			case 4:
//				pmNavigationCheck = "trainingApproval";
//				SubMethodsHR.enrollTrainings(hrPojo);
//				break;
			case 4:
				pmNavigationCheck = "trainingApproval";
				SubMethodsHR.viewAllTrainings(hrPojo);
				break;
			case 5:
				pmNavigationCheck = "Main";
				break;
			case 6:
				pmNavigationCheck = "Exit";
				loginCheck = "No";
				System.out.println("Thank You for using the application.....");
				LoginTasks.updateLogoutDate(hrPojo);
				break;
			default:
				System.out.println("Please enter Valid option");
				break;
			}
		} while (pmNavigationCheck == "trainingApproval");
	}
	
	private void pmLeaveMenu(HRPojo hrPojo) throws SQLException 
	{
		int payrollOption = 0;
		pmNavigationCheck = "Leave";
		do 
		{
			System.out.println("Choose any leave action to perform : " + "\n"
					+ "1. Leave pending requests" + "\n"
					+ "2. Approve Leave" + "\n"
					+ "3. Reject Leave" + "\n"
					+ "4. Back" + "\n"
					+ "5. Logout");
			try 
			{
				payrollOption = Integer.parseInt(pmScreenInput.nextLine());
			}
			catch (NumberFormatException e) 
			{
				//System.out.println(e);
			}
			
			switch (payrollOption) 
			{
			case 1:
				pmNavigationCheck = "Leave";
				SubMethodsPM.pendingRequestsLeave(hrPojo);
				break;
			case 2:
				pmNavigationCheck = "Leave";
				SubMethodsPM.approveLeave(hrPojo);
				break;
			case 3:
				pmNavigationCheck = "Leave";
				SubMethodsPM.rejectLeave(hrPojo);
				break;
			case 4:
				pmNavigationCheck = "Main";
				break;
			case 5:
				pmNavigationCheck = "Exit";
				loginCheck = "No";
				System.out.println("Thank You for using the application.....");
				LoginTasks.updateLogoutDate(hrPojo);
				break;
			default:
				System.out.println("Please enter Valid option");
				break;
			}
		} while (pmNavigationCheck == "Leave");
	}
}