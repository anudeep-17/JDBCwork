package databasehw8;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class Test_Suite 
{
	connection setup = new connection(driver.database_connection_string, driver.database_user_name, driver.database_user_password);
	
	//all-tables
	String student = "student";
	String advisor = "advisor";
	String inst = "instructor";
	String dept = "department";
	String course = "course";
	String prereq = "prereq";
	String takes = "takes";
	String TS = "time_slot";
	String teaches = "teaches";
	String section = "section";
	String CS = "classroom";
	
	
	@Test
	public void estimationtest_student() throws SQLException
	{
		assertEquals(2000, joinestimate.estimatejoin(setup, student, advisor));
		assertEquals(63, joinestimate.estimatejoin(setup, student, inst));
		assertEquals(2000, joinestimate.estimatejoin(setup, student, dept));
		assertEquals(20000, joinestimate.estimatejoin(setup, student, course));
		assertEquals(200000, joinestimate.estimatejoin(setup, student, prereq));
		assertEquals(30000, joinestimate.estimatejoin(setup, student, takes));
		assertEquals(40000, joinestimate.estimatejoin(setup, student, TS));
		assertEquals(200000, joinestimate.estimatejoin(setup, student, teaches));
		assertEquals(200000, joinestimate.estimatejoin(setup, student, section));
		assertEquals(60000, joinestimate.estimatejoin(setup, student, CS));
	}
}
