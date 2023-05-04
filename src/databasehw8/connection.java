package databasehw8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection 
{
	public connection()
	{
		
	}
	
	public connection(Connection conn,String database_connection_string, String database_user_name, String database_user_password )
	  {
		  try {

	            conn = DriverManager.getConnection(database_connection_string, database_user_name, database_user_password );

	            System.out.println("You are successfully connected to the PostgreSQL database server.");

	        } catch (SQLException e) 

	        {

	            System.out.println(e.getMessage());

	        }
	  }
}
