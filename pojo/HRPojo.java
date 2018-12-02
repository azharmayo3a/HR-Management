package pojo;

public class HRPojo {
	
	private int employeeId,employeeSupervisorId,PayrollMonth,PayrollPercentage,ProjectId,trainingId,
	trainingPoints,pincode,addressId,id;
	private String PayrollYear,employeeFirstName,employeeLastName,employeeDOB,employeeGender,employeeDesignation,employeeRole,
	employeeSkillset,trainingTitle,trainingDate,doj,trainingTime,flatNo,streetName,city,userName,password,role,designation,firstName,supervisor;
	private long employeePhoneNumber;
	private char primaryCheck;
	private double hra,performance,allowance,basicPay,monthlyPay;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;	
	}
	public int getGenerateEmployeeId() {
		return employeeId;
	}
	public void setGenerateEmployeeId(int employeeId) {
		this.employeeId = employeeId;	
	}
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;	
	}
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public String getEmployeeDOB() {
		return employeeDOB;
	}
	public void setEmployeeDOB(String employeeDOB) {
		this.employeeDOB = employeeDOB;
	}
	public long getEmployeePhoneNumber() {
		return employeePhoneNumber;
	}
	public void setEmployeePhoneNumber(long employeePhoneNumber) {
		this.employeePhoneNumber = employeePhoneNumber;
	}
	public String getEmployeeGender() {
		return employeeGender;
	}
	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}
	public String getEmployeeDesignation() {
		return employeeDesignation;
	}
	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}
	public String getEmployeeRole() {
		return employeeRole;
	}
	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}
	public int getEmployeeSupervisorId() {
		return employeeSupervisorId;
	}
	public void setEmployeeSupervisorId(int employeeSupervisorId) {
		this.employeeSupervisorId = employeeSupervisorId;
	}
	public String getEmployeeSkillset() {
		return employeeSkillset;
	}
	public void setEmployeeSkillset(String employeeSkillset) {
		this.employeeSkillset = employeeSkillset;
	}
	public int getPayrollMonth() {
		return PayrollMonth;
	}
	public void setPayrollMonth(int PayrollMonth) {
		this.PayrollMonth = PayrollMonth;
	}
	public String getPayrollYear() {
		return PayrollYear;
	}
	public void setPayrollYear(String year) {
		this.PayrollYear = year;
	}
	public int getPayrollPercentage() {
		return PayrollPercentage;
	}
	public void setPayrollPercentage(int PayrollPercentage) {
		this.PayrollPercentage = PayrollPercentage;
	}
	public int getProjectId() {
		return ProjectId;
	}
	public void setProjectId(int ProjectId) {
		this.ProjectId = ProjectId;
	}
	public int getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(int trainingId) {
		this.trainingId = trainingId;
	}
	public String getTrainingTitle() {
		return trainingTitle;
	}
	public void setTrainingTitle(String trainingTitle) {
		this.trainingTitle = trainingTitle;
	}
	public String getTrainingDate() {
		return trainingDate;
	}
	public void setTrainingDate(String trainingDate) {
		this.trainingDate = trainingDate;
	}
	public String getTrainingTime() {
		return trainingTime;
	}
	public void setTrainingTime(String trainingTime) {
		this.trainingTime = trainingTime;
	}
	public int getTrainingPoints() {
		return trainingPoints;
	}
	public void setTrainingPoints(int trainingPoints) {
		this.trainingPoints = trainingPoints;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;	
	}
	public String getUserRole() {
		return role;
	}
	public void setUserRole(String role) {
		this.role = role;	
	}
	public String getUserDesignation() {
		return designation;
	}
	public void setUserDesignation(String designation) {
		this.designation = designation;	
	}
	public String getUserFirstName() {
		return firstName;
	}
	public void setUserFirstName(String firstName) {
		this.firstName = firstName;	
	}
	public String getUserSupervisor() {
		return supervisor;
	}
	public void setUserSupervisor(String supervisor) {
		this.supervisor = supervisor;	
	}
	public String getFlatNo() {
		return flatNo;
	}
	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;	
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;	
	}
	public int getPinCode() {
		return pincode;
	}
	public void setPinCode(int pincode) {
		this.pincode = pincode;	
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;	
	}
	public char getPrimaryCheck() {
		return primaryCheck;
	}
	public void setPrimaryCheck(char primaryCheck) {
		this.primaryCheck = primaryCheck;	
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getBasicPay() {
		return basicPay;
	}
	public void setBasicPay(double basicPay) {
		this.basicPay = basicPay;
	}
	public Double getHRA() {
		return hra;
	}
	public void setHRA(double hra) {
		this.hra = hra;
	}
	public Double getAllowance() {
		return allowance;
	}
	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}
	public Double getPerformance() {
		return performance;
	}
	public void setPerformance(double performance) {
		this.performance = performance;
	}
	public Double getMonthlyPay() {
		return monthlyPay;
	}
	public void setMonthlyPay(double monthlyPay) {
		this.monthlyPay = monthlyPay;
	}
	public String getEmployeeDOJ() {
		return doj;
	}
	public void setEmployeeDOJ(String doj) {
		this.doj = doj;
		
	}
}