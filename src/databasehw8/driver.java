package databasehw8;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;


public class driver 
{

	private static Connection conn = null;
	
	private static String database_connection_string = "jdbc:postgresql://localhost:5432/uni_sk79";

    private static String database_user_name = "postgres";

    private static String database_user_password = "SK796958";
    
	public static void main(String[] args)
	{
		connection setup = new connection(conn, database_connection_string, database_user_name, database_user_password);
		
	}
}
