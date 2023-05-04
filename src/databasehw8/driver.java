package databasehw8;

import java.sql.*;


public class driver 
{
	
	private static String database_connection_string = "jdbc:postgresql://localhost:5432/uni_sk79";

    private static String database_user_name = "postgres";

    private static String database_user_password = "SK796958";
 
    
	public static void main(String[] args) throws SQLException
	{
		connection setup = new connection( database_connection_string, database_user_name, database_user_password);
		int estimation = joinestimate.estimatejoin(setup, "prereq","course");
		System.out.println("Estimated join size: " + estimation);
		
	}
	
	
	
	
	
}
