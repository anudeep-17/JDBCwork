package databasehw8;

import java.sql.*;
import java.util.Scanner;


public class driver 
{
	
	public static String database_connection_string = "jdbc:postgresql://localhost:5432/uni_sk79";

    public static String database_user_name = "postgres";

    public static String database_user_password = "SK796958";
 
    
	public static void main(String[] args) throws SQLException
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("please enter table1 name: ");
		String table1 = sc.nextLine();
		
		System.out.println("please enter table2 name: ");
		String table2 = sc.nextLine();
		
		System.out.println();
		
		System.out.println("-----------------------------------------");
		System.out.println("Join calculations: ");
		
		connection setup = new connection(database_connection_string, database_user_name, database_user_password);
		
		int estimation = joinestimate.estimatejoin(setup, table1, table2);
		int actualsize = actualjoin.actualJoinsize(setup, table1, table2);
		int difference = estimation - actualsize;
		
		System.out.println("Estimated join size: " + estimation);
		System.out.println("actual join size: " + actualsize);
		System.out.println("difference of sizes: " + difference);
	}
	
	
	
}
