package database.HRManagement;

import java.sql.*;

public class DatabaseConnection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String SERVER = "jdbc:mysql://localhost:3306/";
	static final String DATABASE = "hr management";
	static final String USERNAME = "root";
	static final String PASSWORD = "root";
	static Connection connection = null;
	static Statement statement = null;

	//	private static DatabaseConnection dbConnectorObj;

	public DatabaseConnection()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(SERVER + DATABASE, USERNAME, PASSWORD);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public  Connection getConnection() 
	{
		return connection;
	}
}