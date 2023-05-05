package databasehw8;

import java.sql.*;
import java.util.Scanner;


public class driver 
{
	
	public static String database_connection_string = "jdbc:postgresql://localhost:5432/uni_sk79"; //database url

    public static String database_user_name = "postgres"; //database username

    public static String database_user_password = "SK796958"; //database password
 
    
	public static void main(String[] args) throws SQLException
	{		
		//edge case check checks if arguments have less than 2 or more than 2 or have no argument to make sure not to enter the program
		if(args.length < 2 || args.length > 2 || args == null)
		{
			System.out.println("please check your arguments and re try");
			return ;
		}
		
		//assigns the arguments
		
		String table1 = args[0]; 
		
		String table2 = args[1];
		
		//connection setup using connection class 
		connection setup = new connection(database_connection_string, database_user_name, database_user_password);
		Connection usage = setup.conn; // connection 
		
		//check if the table exists in the database else will break
		
		if(connection.dotable_exist(usage, table1, table2))
		{
			System.out.println();
			System.out.println("-----------------------------------------");
			System.out.println("Join calculations: ");
			
			if(table1 == null || table2 == null)
			{
				System.out.println("one of the table name is wrong");
				return ;
			}
			
			int estimation = joinestimate.estimatejoin(usage, table1, table2); //claculates and returns estimated join
			int actualsize = actualjoin.actualJoinsize(usage, table1, table2); //calculates the actual join
			int difference = estimation - actualsize; //calculatiuon of difference
			
			//prompt of the results.
			System.out.println("Estimated join size: " + estimation);
			System.out.println("actual join size: " + actualsize);
			System.out.println("difference of sizes: " + difference);
		}
		else
		{
			System.out.println();
			System.out.println("please enter table name thats in database");
		}
		
	}
	
}
