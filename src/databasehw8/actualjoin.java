package databasehw8;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class actualjoin 
{
	//runs the query of actual join and returns the coun / size of it.
	public static int actualJoinsize(Connection setup, String table1, String table2) throws SQLException
	{
		ResultSet count = connection.queryresult(setup, "select count(*) as count from " +table1+ " natural join "+ table2);
		
		while(count.next())
		{
			return count.getInt("count");
		}
		
		count.close();
		
		return 0;
	}

}
