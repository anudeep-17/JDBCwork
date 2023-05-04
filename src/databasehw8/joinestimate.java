package databasehw8;

import java.sql.ResultSet;
import java.sql.SQLException;

public class joinestimate 
{	
	
	public static void estimatejoin(connection setup, String table1, String table2) throws SQLException
	{
	
		
		ResultSet set1 = connection.getmetadata(setup).getPrimaryKeys(null, null,table1);
		ResultSet set2 = connection.getmetadata(setup).getImportedKeys(null, null,table1);
		ResultSet set3 = connection.getmetadata(setup).getExportedKeys(null, null,table1);
		
		
		while(set1.next() && set2.next() && set3.next())
		{
			System.out.println("primary key : " + set1.getString("COLUMN_NAME"));
			System.out.println("foreign keys : " + set2.getString("PKTABLE_NAME") + " " + set2.getString("FKCOLUMN_NAME"));
			System.out.println("exported keys : " + set3.getString("FKTABLE_NAME") + " " + set3.getString("FKCOLUMN_NAME"));
		}
	
	}
	
}
