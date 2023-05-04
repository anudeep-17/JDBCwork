package databasehw8;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connection 
{
	private static Connection conn = null;
	
	public connection()
	{
		
	}
	
	public connection(String database_connection_string, String database_user_name, String database_user_password )
	  {
		  try {

	            conn = DriverManager.getConnection(database_connection_string, database_user_name, database_user_password );

	            System.out.println("You are successfully connected to the PostgreSQL database server.");

	        } catch (SQLException e) 

	        {

	            System.out.println(e.getMessage());

	        }
	  }
	
	public static Statement createStatement(connection Conn) throws SQLException
	{
		return conn.createStatement(); 
	}
	
	public static DatabaseMetaData getmetadata(connection Conn) throws SQLException
	{
		return conn.getMetaData();
	}
	
	public static ResultSet queryresult(connection Conn, String query) throws SQLException
	{
		return createStatement(Conn).executeQuery(query);
	}
	
}
