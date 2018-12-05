package employee.HRManagement;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import hr.HRManagement.SubMethodsHR;
import main.HRManagement.LoginTasks;
import pojo.HRPojo;

public class EmployeeMainMenu 
{
	Scanner employeeScreenInput = new Scanner(System.in);
	
	String employeeNavigationCheck = "Main";
	
	static HRPojo hrPojo = new HRPojo();

	public void employeeMainMenu(HRPojo hrPojo) throws SQLException, ParseException 
	{
		int optionEmployee = 0;
		do 
		{
			System.out.println("Choose any option to perform : " + "\n"
					+ "1. Profile" + "\n"
					+ "2. Trainings" + "\n"
					+ "3. Skillset and Payroll " + "\n"
					+ "4. Leave " + "\n"
					+ "5. Logout");
			try 
			{
				optionEmployee = Integer.parseInt(employeeScreenInput.nextLine());
			}
			catch (NumberFormatException e) 
			{
				//System.out.println(e);
			}
			switch (optionEmployee) 
			{
			case 1:
				employeeProfileMenu(hrPojo);
				break;
			case 2:
				employeeTrainingsMenu(hrPojo);
				break;
			case 3:
				employeeSkillsetPayrollMenu(hrPojo);
				break;
			case 4:
				employeeLeaveMenu(hrPojo);
				break;
			case 5:
				employeeNavigationCheck = "Payroll";
				System.out.println("Thank You for using the application.....");
				LoginTasks.updateLogoutDate(hrPojo);
				break;
			default:
				System.out.println("Please enter Valid option");
				break;
			}
		} while (employeeNavigationCheck == "Main");
	}

	private void employeeLeaveMenu(HRPojo hrPojo) throws SQLException 
	{
		int employeeLeaveOption = 0;
		employeeNavigationCheck = "Leave";
		do 
		{
			System.out.println("Choose any option to perform : " + "\n"
					+ "1. Apply Leave" + "\n"
					+ "2. View Leave requests" + "\n"
					+ "3. View Leave balance" + "\n"
					+ "4. Cancel Leave request" + "\n"
					+ "5. Back"+ "\n"
					+ "6. Logout");
			try 
			{
				employeeLeaveOption = Integer.parseInt(employeeScreenInput.nextLine());
			}
			catch (NumberFormatException e) 
			{
				//System.out.println(e);
			}
			
			switch (employeeLeaveOption) 
			{
			case 1:
				employeeNavigationCheck = "Leave";
				SubMethodsEmployee.applyLeave(hrPojo);
				break;
			case 2:
				employeeNavigationCheck = "Leave";
				SubMethodsEmployee.viewLeaveRequests(hrPojo);
				break;
			case 3:
				employeeNavigationCheck = "Leave";
				SubMethodsEmployee.viewLeaveBalance(hrPojo);
				break;
			case 4:
				employeeNavigationCheck = "Leave";
				SubMethodsEmployee.cancelLeaveRequest(hrPojo);
				break;
			case 5:
				employeeNavigationCheck = "Main";
				break;
			case 6:
				employeeNavigationCheck = "Exit";
				System.out.println("Thank You for using the application.....");
				LoginTasks.updateLogoutDate(hrPojo);
				break;
			default:
				System.out.println("Please enter Valid option");
				break;
			}
		} while (employeeNavigationCheck == "Leave");
		
	}

	private void employeeSkillsetPayrollMenu(HRPojo hrPojo) throws SQLException, ParseException 
	{
		int employeePayrollOption = 0;
		employeeNavigationCheck = "Payroll";
		do 
		{
			System.out.println("Choose any option to perform : " + "\n"
					+ "1. View Payroll" + "\n"
					+ "2. View Skillset" + "\n"
					+ "3. Add Skillset" + "\n"
					+ "4. Edit Skillset" + "\n"
					+ "5. Back"+ "\n"
					+ "6. Logout");
			try 
			{
				employeePayrollOption = Integer.parseInt(employeeScreenInput.nextLine());
			}
			catch (NumberFormatException e) 
			{
				//System.out.println(e);
			}
			
			switch (employeePayrollOption) 
			{
			case 1:
				employeeNavigationCheck = "Payroll";
				SubMethodsHR.viewPayroll(hrPojo);
				break;
			case 2:
				employeeNavigationCheck = "Payroll";
				SubMethodsEmployee.viewSkillset(hrPojo);
				break;
			case 3:
				employeeNavigationCheck = "Payroll";
				SubMethodsEmployee.addSkillset(hrPojo);
				break;
			case 4:
				employeeNavigationCheck = "Payroll";
				SubMethodsEmployee.editSkillset(hrPojo);
				break;
			case 5:
				employeeNavigationCheck = "Main";
				break;
			case 6:
				employeeNavigationCheck = "Exit";
				System.out.println("Thank You for using the application.....");
				LoginTasks.updateLogoutDate(hrPojo);
				break;
			default:
				System.out.println("Please enter Valid option");
				break;
			}
		} while (employeeNavigationCheck == "Payroll");

	}

	private void employeeTrainingsMenu(HRPojo hrPojo) throws SQLException 
	{
		int employeeTrainingOption = 0;
		employeeNavigationCheck = "Trainings";
		do 
		{
			System.out.println("Choose any option to perform : " + "\n"
					+ "1. Enroll Training"+ "\n"
					+ "2. view all Trainings" + "\n"
					+ "3. Training History" + "\n"
					+ "4. Back" + "\n"
					+ "5. Logout");
			
			try 
			{
				employeeTrainingOption = Integer.parseInt(employeeScreenInput.nextLine());
			}
			catch (NumberFormatException e) 
			{
				//System.out.println(e);
			}

			switch (employeeTrainingOption) 
			{
			case 1:
				employeeNavigationCheck = "Trainings";
				SubMethodsHR.enrollTrainings(hrPojo);
				break;
			case 2:
				employeeNavigationCheck = "Trainings";
				SubMethodsHR.viewAllTrainings(hrPojo);
				break;
			case 3:
				employeeNavigationCheck = "Trainings";
				SubMethodsHR.viewTrainingsRegistered(hrPojo);
				break;
			case 4:
				employeeNavigationCheck = "Main";
				break;
			case 5:
				employeeNavigationCheck = "Exit";
				System.out.println("Thank You for using the application.....");
				LoginTasks.updateLogoutDate(hrPojo);
				break;
			default:
				System.out.println("Please enter Valid option");
				break;
			}
		} while (employeeNavigationCheck == "Trainings");

	}

	private void employeeProfileMenu(HRPojo hrPojo) throws SQLException 
	{
		int employeeOption = 0;
		employeeNavigationCheck = "profile";
		do 
		{
			System.out.println("Choose any Employee action to perform : " + "\n"
					+ "1. View Profile"+ "\n"
					+ "2. Add Address" + "\n"
					+ "3. View Address" + "\n"
					+ "4. Delete Address" + "\n"
					+ "5. Change Password" + "\n"
					+ "6. Back" + "\n"
					+ "7. Logout");
			try 
			{
				employeeOption = Integer.parseInt(employeeScreenInput.nextLine());
			}
			catch (NumberFormatException e) 
			{
				//System.out.println(e);
			}

			switch (employeeOption) 
			{
			case 1:
				employeeNavigationCheck = "profile";
				SubMethodsEmployee.viewProfile(hrPojo);
				break;
			case 2:
				employeeNavigationCheck = "profile";
				SubMethodsEmployee.addAddress(hrPojo);
				break;
			case 3:
				employeeNavigationCheck = "profile";
				SubMethodsEmployee.viewAddress(hrPojo);
				break;
			case 4:
				employeeNavigationCheck = "profile";
				SubMethodsEmployee.deleteAddress(hrPojo);
				break;
			case 5:
				employeeNavigationCheck = "profile";
				SubMethodsEmployee.changePassword(hrPojo);
				break;
			case 6:
				employeeNavigationCheck = "Main";
				break;
			case 7:
				employeeNavigationCheck = "Exit";
				System.out.println("Thank You for using the application.....");
				LoginTasks.updateLogoutDate(hrPojo);
				break;
			default:
				System.out.println("Please enter Valid option");
				break;
			}
		} while (employeeNavigationCheck == "profile");
		
	}
}