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
		ArrayList<String> is_nonkeycommon = is_a_nonkey_common(setup, table1, table2);
//		System.out.println(is_nonkeycommon);
		
		if(is_a_key_shared(setup, table1, table2))
			{
				//case 2
				Size = sizeoftable(setup, foreigntablename);
			}
		else if(is_nonkeycommon != null)
			{
				//case3
				Size = calc_if_nonkey_common(setup, is_nonkeycommon, table1, table2);
			}
		else
			{
				//case 1
				Size = sizeoftable(setup, table1) * sizeoftable(setup, table2);
			}
		
		return Size;
	}

	
	public static ArrayList is_a_nonkey_common(connection setup,String table1, String table2) throws SQLException
	{
		ResultSet set_table1_1 = connection.getmetadata(setup).getColumns(null, null, table1, null);
		ResultSet set_table2_1 = connection.getmetadata(setup).getColumns(null, null, table2, null);
		
		ResultSet primarykey1 = connection.getmetadata(setup).getPrimaryKeys(null, null,table1);
		ResultSet primarykey2 = connection.getmetadata(setup).getPrimaryKeys(null, null,table2);
		
		ArrayList<String> primarykey_table1 = new ArrayList<String>();
		ArrayList<String> primarykey_table2 = new ArrayList<String>();
		
		ArrayList<String> set1 = new ArrayList<String>();
		ArrayList<String> set2 = new ArrayList<String>();
		
		while(primarykey1.next())
		{
			primarykey_table1.add(primarykey1.getString("COLUMN_NAME"));
		}
		
		while(primarykey2.next())
		{
			primarykey_table2.add(primarykey2.getString("COLUMN_NAME"));
		}
		
		while(set_table1_1.next())
		{
			set1.add(set_table1_1.getString("COLUMN_NAME"));
		}
		
		while(set_table2_1.next())
		{
				set2.add(set_table2_1.getString("COLUMN_NAME"));
		}
		
		set_table1_1.close();
		set_table2_1.close();
		primarykey1.close();
		primarykey2.close();
		
		set1.retainAll(set2);
		
		if(primarykey_table1.containsAll(set1) || primarykey_table2.containsAll(set1))
		{
			return null;
		}
		return set1;
		
	}
	
	public static int calc_if_nonkey_common(connection setup, ArrayList<String> common, String table1, String table2) throws SQLException
	{
		int min = Integer.MAX_VALUE;
		String common_column_names = " ";
		
		for (int i = 0; i < common.size(); i++)
		{
			if(common_column_names.equals(" "))
			{
				common_column_names += common.get(i); 
			}
			else
			{
				common_column_names += ", "+ common.get(i); 
			}
		}
		
		int temp1 = (sizeoftable(setup, table1) * sizeoftable(setup, table2)) / distinctsize(setup, table1, common_column_names);
		int temp2 = (sizeoftable(setup, table1) * sizeoftable(setup, table2)) / distinctsize(setup, table2, common_column_names);
		min = Math.min(min, Math.min(temp1, temp2));
		return min;
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
		
		ArrayList<String> primarykey_table1 = new ArrayList<String>();
		
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
		
//		System.out.println("table1 data: "+  primarykey_table1 +" " + Importedkeytable_table1 +" "+ Exportedkeytable_table1);
//		System.out.println("table2 data: "+ primarykey_table2 +" " + Importedkeytable_table2 +" "+ Exportedkeytable_table2);
//	
		set_table1_1.close();
		set_table1_2.close();
		set_table1_3.close();
		
		set_table2_1.close();
		set_table2_2.close();
		set_table2_3.close();
		
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
	
	//helper
	
	public static int sizeoftable(connection setup,String table) throws SQLException
	{
		ResultSet size_set = connection.queryresult(setup, "select count(*) as count from "+ table);
		
		while(size_set.next())
		{
			return size_set.getInt("count");
		}
		
		size_set.close();
		
		return 0;
	}
	
	
	public static int distinctsize(connection setup,String table, String Column) throws SQLException
	{
		ResultSet size_set = connection.queryresult(setup, "select count(distinct( "+ Column +" )) as count from "+ table);
		
		while(size_set.next())
		{
			return size_set.getInt("count");
		}
		
		size_set.close();
		
		return 0;
	}
	
	
	
	
}
