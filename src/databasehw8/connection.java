package databasehw8;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connection 
{
	public static Connection conn = null;
	
	
	public connection()
	{
		//empty constructor
	}
	
	//connection establishing method
	
	public connection(String database_connection_string, String database_user_name, String database_user_password )
	  {
		  try {

			  	// authenticates and assigns the connection base to the conn vaiable
	            conn = DriverManager.getConnection(database_connection_string, database_user_name, database_user_password );
     
	            System.out.println("You are successfully connected to the PostgreSQL database server.");

	        } catch (SQLException e) 

	        {

	            System.out.println(e.getMessage());

	        }
	  }
	
	
	//-------------------helper----------------------------------
	//create statement helps to create a statment to run a query using the connection variable.
	
	public static Statement createStatement(Connection Conn) throws SQLException
	{
		return conn.createStatement(); 
	}
	
	//getmetadata helps in getting the metadata of the connection
	public static DatabaseMetaData getmetadata(Connection Conn) throws SQLException
	{
		return conn.getMetaData();
	}
	
	//runs the query and returns a resultset to make it easy
	public static ResultSet queryresult(Connection Conn, String query) throws SQLException
	{
		return createStatement(Conn).executeQuery(query);
	}
	
	//runs and returns query result used to run count queries
	public static int countqueryresult_returner(Connection Conn, String query) throws SQLException
	{
		ResultSet set = createStatement(Conn).executeQuery(query);
		while(set.next())
		{
			return set.getInt("count");
		}
		return 0;
	}
	
	//to check if the table exists in database or not 
	public static boolean dotable_exist(Connection setup, String table1, String table2) throws SQLException
	{ 
		ResultSet set1 = getmetadata(setup).getTables(null, null,table1, null); //return if table
		ResultSet set2 = getmetadata(setup).getTables(null, null,table2, null); // return if table exists 
		
		return set1.next() && set2.next(); //if both are true we get true
	}
	
}
