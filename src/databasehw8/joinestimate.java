package databasehw8;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class joinestimate 
{	
	
	private static String foreigntablename = "";
	
	public static int estimatejoin(connection setup, String table1, String table2) throws SQLException
	{
		int Size = 0;
		
		if(is_a_key_shared(setup, table1, table2))
		  {
			//case 2
			  Size = sizeoftable(setup, foreigntablename);
		  }
		else
		{
			//case 1
			Size = sizeoftable(setup, table1) * sizeoftable(setup, table2);
		}
		
		return Size;
	}
	
	
	public static int sizeoftable(connection setup,String table) throws SQLException
	{
		ResultSet size_set = connection.queryresult(setup, "select count(*) as count from "+ table);
		
		while(size_set.next())
		{
			return size_set.getInt("count");
		}
		return 0;
	}
	
	
	public static boolean is_a_key_shared(connection setup,String table1, String table2) throws SQLException
	{
		//table1
		ResultSet set_table1_1 = connection.getmetadata(setup).getPrimaryKeys(null, null,table1);
		ResultSet set_table1_2 = connection.getmetadata(setup).getImportedKeys(null, null,table1);
		ResultSet set_table1_3 = connection.getmetadata(setup).getExportedKeys(null, null,table1);
		
		//table2 
		ResultSet set_table2_1 = connection.getmetadata(setup).getPrimaryKeys(null, null,table2);
		ResultSet set_table2_2 = connection.getmetadata(setup).getImportedKeys(null, null,table2);
		ResultSet set_table2_3 = connection.getmetadata(setup).getExportedKeys(null, null,table2);
		
		ArrayList<String> primarykey_table1 = new ArrayList<String>();;
		
		ArrayList<String> Importedkey_table1 = new ArrayList<String>();
		ArrayList<String>  Importedkeytable_table1 = new ArrayList<String>();
		
		ArrayList<String> Exportedkey_table1 = new ArrayList<String>();
		ArrayList<String>  Exportedkeytable_table1 = new ArrayList<String>();
		
		ArrayList<String> primarykey_table2 = new ArrayList<String>();
		
		ArrayList<String> Importedkey_table2 = new ArrayList<String>();
		ArrayList<String> Importedkeytable_table2 = new ArrayList<String>();
		
		ArrayList<String> Exportedkey_table2 = new ArrayList<String>();
		ArrayList<String>  Exportedkeytable_table2 = new ArrayList<String>();
		
		
		while(set_table1_1.next())
		{
			primarykey_table1.add(set_table1_1.getString("COLUMN_NAME"));
		}
		
		while(set_table2_1.next())
		{
			primarykey_table2.add(set_table2_1.getString("COLUMN_NAME"));
		}
		
		
		while(set_table1_2.next())
		{
			Importedkeytable_table1.add(set_table1_2.getString("PKTABLE_NAME"));
			Importedkey_table1.add(set_table1_2.getString("PKCOLUMN_NAME"));
		}
		
		while(set_table2_2.next())
		{
			Importedkeytable_table2.add(set_table2_2.getString("PKTABLE_NAME"));
			Importedkey_table2.add(set_table2_2.getString("PKCOLUMN_NAME"));
		}
		
		
		while(set_table1_3.next())
		{
			Exportedkeytable_table1.add(set_table1_3.getString("FKTABLE_NAME"));
			Exportedkey_table1.add(set_table1_3.getString("FKCOLUMN_NAME"));
		}
		
		while(set_table2_3.next())
		{
			Exportedkeytable_table2.add(set_table2_3.getString("FKTABLE_NAME"));
			Exportedkey_table2.add(set_table2_3.getString("FKCOLUMN_NAME"));
		}
		
//		System.out.println( primarykey_table1 +" " + Importedkeytable_table1 +" "+ Exportedkeytable_table1);
//		System.out.println( primarykey_table2 +" " + Importedkeytable_table2 +" "+ Exportedkeytable_table2);
	
		
		boolean case1 = Importedkey_table2.containsAll(primarykey_table1) && Exportedkeytable_table1.contains(table2) && Importedkeytable_table2.contains(table1); //table2 has table1 primary key
		
		boolean case2 = Importedkey_table1.containsAll(primarykey_table2)&& Exportedkeytable_table2.contains(table1) && Importedkeytable_table1.contains(table2);//table1 has table2 primary key
		
		if(case1 || case2)
		{
			if(case1) {foreigntablename = table2;}
			if(case2) {foreigntablename = table1;}
			return true;
		}
		return false;
	}
}
