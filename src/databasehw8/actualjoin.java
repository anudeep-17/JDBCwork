package databasehw8;

import java.sql.ResultSet;
import java.sql.SQLException;

public class actualjoin 
{
	
	public static int actualJoinsize(connection setup, String table1, String table2) throws SQLException
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
