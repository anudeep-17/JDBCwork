package databasehw8;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class joinestimate 
{	
	
	private static String foreigntablename = ""; //foreign key table name to calculate the sizes
	
	//computes the estimation of a join 
	
	public static int estimatejoin(Connection setup, String table1, String table2) throws SQLException
	{
		int Size = 0;
		ArrayList<String> is_nonkeycommon = is_a_nonkey_common(setup, table1, table2);
		
		if(is_a_key_shared(setup, table1, table2)) //if table1 and table2 has primary key or foreign key or each other.
			{
				//case 2 ----------as per text book
				Size = sizeoftable(setup, foreigntablename);
			}
		else if(is_nonkeycommon.size() != 0) //else if they have non keys in common
			{
				//case3 -------------- as per textbook 

				Size = calc_if_nonkey_common(setup, is_nonkeycommon, table1, table2); //calc the non common size 
			}
		else
			{
				//case 1 ---------- as per text book
				Size = sizeoftable(setup, table1) * sizeoftable(setup, table2); //if non of the above is true then cartesian product
			}
		 
		return Size; //return the calculated size
	}

	
	//called to find out if a non common set of columns are identified between table 1 and table2 
	public static ArrayList is_a_nonkey_common(Connection setup,String table1, String table2) throws SQLException
	{
		//gets all columns of table1 and table2
		ResultSet set_table1_1 = connection.getmetadata(setup).getColumns(null, null, table1, null);
		ResultSet set_table2_1 = connection.getmetadata(setup).getColumns(null, null, table2, null);
		
		//gets all primary keys of table1 and table 2
		ResultSet primarykey1 = connection.getmetadata(setup).getPrimaryKeys(null, null,table1);
		ResultSet primarykey2 = connection.getmetadata(setup).getPrimaryKeys(null, null,table2);
		
		// array lists to store and compare the data
		ArrayList<String> primarykey_table1 = new ArrayList<String>();
		ArrayList<String> primarykey_table2 = new ArrayList<String>();
		
		ArrayList<String> set1 = new ArrayList<String>();
		ArrayList<String> set2 = new ArrayList<String>();
		
		//while the result sets have the data in them will run and fill the array lists. 
		while(primarykey1.next())
		{
			primarykey_table1.add(primarykey1.getString("COLUMN_NAME")); //fills columns names of the primary key
		}
		
		while(primarykey2.next())
		{
			primarykey_table2.add(primarykey2.getString("COLUMN_NAME")); //fills columns names of the primary key
		}
		
		while(set_table1_1.next())
		{
			set1.add(set_table1_1.getString("COLUMN_NAME")); //fills all column names
		}
		
		while(set_table2_1.next())
		{
				set2.add(set_table2_1.getString("COLUMN_NAME")); //fills all column names
		}
		//closes all result sets 
		set_table1_1.close();
		set_table2_1.close();
		primarykey1.close();
		primarykey2.close();
		
		set1.retainAll(set2); //retains only the common values and removes all other data.
	
		return set1; //return the intersection values.
		
	}
	
	//if a non key set of columns identified we call this to calculate the size.
	
	public static int calc_if_nonkey_common(Connection setup, ArrayList<String> common, String table1, String table2) throws SQLException
	{ 
		int min = Integer.MAX_VALUE; //assigns a max value so that it can be replaced by min on comparision.
		String common_column_names = " "; 
		
		for (int i = 0; i < common.size(); i++)
		{
			//reads through the common values and appends the column names to the string in a way that it is queriable
			
			if(common_column_names.equals(" "))
			{
				common_column_names += common.get(i); 
			}
			else
			{
				common_column_names += ", "+ common.get(i); 
			}
		}
		
//		System.out.println(common_column_names);
		
		//calculation using nR*nS / v(A,r) and nR*nS/ v(A,s) to findout min between them
		int temp1 = (sizeoftable(setup, table1) * sizeoftable(setup, table2)) / distinctsize(setup, table1, common_column_names);
		int temp2 = (sizeoftable(setup, table1) * sizeoftable(setup, table2)) / distinctsize(setup, table2, common_column_names);
		
		//calculating the min value of both of these calculations.
		min = Math.min(min, Math.min(temp1, temp2));
		return min; //returns min size of the calculation
	}
	
	//if one of the encountered is a primary key or foreign key
	public static boolean is_a_key_shared(Connection setup,String table1, String table2) throws SQLException
	{
		//table1
		ResultSet set_table1_1 = connection.getmetadata(setup).getPrimaryKeys(null, null,table1); //gets primary keys
		ResultSet set_table1_2 = connection.getmetadata(setup).getImportedKeys(null, null,table1); //gets imported keys
		ResultSet set_table1_3 = connection.getmetadata(setup).getExportedKeys(null, null,table1); //gets exported keys
		
		//table2 
		ResultSet set_table2_1 = connection.getmetadata(setup).getPrimaryKeys(null, null,table2); //gets primary keys
		ResultSet set_table2_2 = connection.getmetadata(setup).getImportedKeys(null, null,table2); // gets imported keys
		ResultSet set_table2_3 = connection.getmetadata(setup).getExportedKeys(null, null,table2); // gets exported keys 
		
		//arrays lists to store all the above result set data to compare and decide the result
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
		
		//on the result set having data it will add the data to array list.
		while(set_table1_1.next())
		{
			primarykey_table1.add(set_table1_1.getString("COLUMN_NAME")); //adds all primary keys 
		}
		
		while(set_table2_1.next())
		{
			primarykey_table2.add(set_table2_1.getString("COLUMN_NAME"));//adds all primary keys 
		}
		
		
		while(set_table1_2.next())
		{
			Importedkeytable_table1.add(set_table1_2.getString("PKTABLE_NAME")); //adds all imported key table names
			Importedkey_table1.add(set_table1_2.getString("PKCOLUMN_NAME"));  //adds all imported keys
		}
		
		while(set_table2_2.next())
		{
			Importedkeytable_table2.add(set_table2_2.getString("PKTABLE_NAME")); //adds all imported key table names
			Importedkey_table2.add(set_table2_2.getString("PKCOLUMN_NAME")); //adds all imported keys
		}
		
		
		while(set_table1_3.next())
		{
			Exportedkeytable_table1.add(set_table1_3.getString("FKTABLE_NAME")); //adds all exported keys
			Exportedkey_table1.add(set_table1_3.getString("FKCOLUMN_NAME")); //adds all exported keys
		}
		
		while(set_table2_3.next())
		{
			Exportedkeytable_table2.add(set_table2_3.getString("FKTABLE_NAME")); //adds all exported keys
			Exportedkey_table2.add(set_table2_3.getString("FKCOLUMN_NAME")); //adds all exported keys
		}
		
//		System.out.println("table1 data: "+  primarykey_table1 +" " + Importedkeytable_table1 +" "+ Exportedkeytable_table1);
//		System.out.println("table2 data: "+ primarykey_table2 +" " + Importedkeytable_table2 +" "+ Exportedkeytable_table2);
		
//	 	closes all the result sets
		set_table1_1.close();
		set_table1_2.close();
		set_table1_3.close();
		
		set_table2_1.close();
		set_table2_2.close();
		set_table2_3.close();
		
		//case 1 : checks if the imported keys of table2 are the primary keys of table1 implying  => table2 has forign key 
		// 		   1. exported key table names of table1 has table2 in it 
	    //		   2. imported key table names of table2 has table1 in it.
		boolean case1 = Importedkey_table2.containsAll(primarykey_table1) && Exportedkeytable_table1.contains(table2) && Importedkeytable_table2.contains(table1); //table2 has table1 primary key
		
		//case 1 : checks if the imported keys of table1 are the primary keys of table2 implying  => table1 has forign key 
				// 		   1. exported key table names of table2 has table1 in it 
			    //		   2. imported key table names of table1 has table2 in it.
		
		boolean case2 = Importedkey_table1.containsAll(primarykey_table2)&& Exportedkeytable_table2.contains(table1) && Importedkeytable_table1.contains(table2);//table1 has table2 primary key
		
		//if any of these is true
		if(case1 || case2)
		{
			if(case1) {foreigntablename = table2;} //if case 1 is true then table 2 is the foriegn key
			if(case2) {foreigntablename = table1;} //else if case 2 is true then table 1 is foreign key
			return true; //return boolen true for the estimatejoin function usability
		}
		
		return false; //if both are false then return false.
	} 
	
	//helper
	//calculates the size of the table by running a query 
	public static int sizeoftable(Connection setup,String table) throws SQLException
	{
		//runs the given query and gets the result set to iterate over and find the size of the table.
		ResultSet size_set = connection.queryresult(setup, "select count(*) as count from "+ table);
		
		while(size_set.next())
		{
			return size_set.getInt("count");
		}
		
		size_set.close();
		
		return 0;
	}
	
	//is used for case 3 consideration when we have a common set
	public static int distinctsize(Connection setup,String table, String Column) throws SQLException
	{
		// runs through the query and gets the distinct size.
		ResultSet size_set = connection.queryresult(setup, "select count(distinct("+ Column +")) from "+ table);
		
		while(size_set.next())
		{
			return size_set.getInt("count");
		}
		
		size_set.close();
		
		return 0;
	}
	
	

	
}
