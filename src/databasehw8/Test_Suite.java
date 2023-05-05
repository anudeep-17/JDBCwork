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
		assertEquals(50, joinestimate.estimatejoin(setup, student, inst));
		assertEquals(2000, joinestimate.estimatejoin(setup, student, dept));
		assertEquals(20000, joinestimate.estimatejoin(setup, student, course));
		assertEquals(200000, joinestimate.estimatejoin(setup, student, prereq));
		assertEquals(30000, joinestimate.estimatejoin(setup, student, takes));
		assertEquals(40000, joinestimate.estimatejoin(setup, student, TS));
		assertEquals(200000, joinestimate.estimatejoin(setup, student, teaches));
		assertEquals(200000, joinestimate.estimatejoin(setup, student, section));
		assertEquals(60000, joinestimate.estimatejoin(setup, student, CS));
	}
	
	@Test
	public void estimationtest_advisor() throws SQLException
	{
		assertEquals(2000, joinestimate.estimatejoin(setup, advisor, student));
		assertEquals(2000, joinestimate.estimatejoin(setup, advisor, inst));
		assertEquals(40000, joinestimate.estimatejoin(setup, advisor, dept));
		assertEquals(400000, joinestimate.estimatejoin(setup, advisor, course));
		assertEquals(200000, joinestimate.estimatejoin(setup, advisor, prereq));
		assertEquals(60000000, joinestimate.estimatejoin(setup, advisor, takes));
		assertEquals(40000, joinestimate.estimatejoin(setup, advisor, TS));
		assertEquals(200000, joinestimate.estimatejoin(setup, advisor, teaches));
		assertEquals(200000, joinestimate.estimatejoin(setup, advisor, section));
		assertEquals(60000, joinestimate.estimatejoin(setup, advisor, CS));
	}
	
	@Test
	public void estimationtest_instructor() throws SQLException
	{
		String check = inst;
		assertEquals(50, joinestimate.estimatejoin(setup, check, student));
		assertEquals(2000, joinestimate.estimatejoin(setup, check, advisor));
		assertEquals(50, joinestimate.estimatejoin(setup, check, dept));
		assertEquals(500, joinestimate.estimatejoin(setup, check, course));
		assertEquals(5000, joinestimate.estimatejoin(setup, check, prereq));
		assertEquals(1500000, joinestimate.estimatejoin(setup, check, takes));
		assertEquals(1000, joinestimate.estimatejoin(setup, check, TS));
		assertEquals(100, joinestimate.estimatejoin(setup, check, teaches));
		assertEquals(5000, joinestimate.estimatejoin(setup, check, section));
		assertEquals(1500, joinestimate.estimatejoin(setup, check, CS));
	}
	
	@Test
	public void estimationtest_dept() throws SQLException
	{
		String check = dept;
		assertEquals(2000, joinestimate.estimatejoin(setup, check, student));
		assertEquals(40000, joinestimate.estimatejoin(setup, check, advisor));
		assertEquals(50, joinestimate.estimatejoin(setup, check, inst));
		assertEquals(200, joinestimate.estimatejoin(setup, check, course));
		assertEquals(2000, joinestimate.estimatejoin(setup, check, prereq));
		assertEquals(600000, joinestimate.estimatejoin(setup, check, takes));
		assertEquals(400, joinestimate.estimatejoin(setup, check, TS));
		assertEquals(2000, joinestimate.estimatejoin(setup, check, teaches));
		assertEquals(111, joinestimate.estimatejoin(setup, check, section));
		assertEquals(600, joinestimate.estimatejoin(setup, check, CS));
	}
	
	@Test
	public void estimationtest_course() throws SQLException
	{
		String check = course;
		assertEquals(20000, joinestimate.estimatejoin(setup, check, student));
		assertEquals(400000, joinestimate.estimatejoin(setup, check, advisor));
		assertEquals(500, joinestimate.estimatejoin(setup, check, inst));
		assertEquals(200, joinestimate.estimatejoin(setup, check, dept));
		assertEquals(100, joinestimate.estimatejoin(setup, check, prereq));
		assertEquals(6000000, joinestimate.estimatejoin(setup, check, takes));
		assertEquals(4000, joinestimate.estimatejoin(setup, check, TS));
		assertEquals(20000, joinestimate.estimatejoin(setup, check, teaches));
		assertEquals(100, joinestimate.estimatejoin(setup, check, section));
		assertEquals(6000, joinestimate.estimatejoin(setup, check, CS));
	}
	
	@Test
	public void estimationtest_prereq() throws SQLException
	{
		String check = prereq ;
		assertEquals(200000, joinestimate.estimatejoin(setup, check, student));
		assertEquals(200000, joinestimate.estimatejoin(setup, check, advisor));
		assertEquals(5000, joinestimate.estimatejoin(setup, check, inst));
		assertEquals(2000, joinestimate.estimatejoin(setup, check, dept));
		assertEquals(100, joinestimate.estimatejoin(setup, check, course));
		assertEquals(3000000, joinestimate.estimatejoin(setup, check, takes));
		assertEquals(2000, joinestimate.estimatejoin(setup, check, TS));
		assertEquals(10000, joinestimate.estimatejoin(setup, check, teaches));
		assertEquals(10000, joinestimate.estimatejoin(setup, check, section));
		assertEquals(3000, joinestimate.estimatejoin(setup, check, CS));
	}
	
	@Test
	public void estimationtest_takes() throws SQLException
	{
		String check = takes;
		assertEquals(30000, joinestimate.estimatejoin(setup, check, student));
		assertEquals(60000000, joinestimate.estimatejoin(setup, check, advisor));
		assertEquals(1500000, joinestimate.estimatejoin(setup, check, inst));
		assertEquals(600000, joinestimate.estimatejoin(setup, check, dept));
		assertEquals(6000000, joinestimate.estimatejoin(setup, check, course));
		assertEquals(3000000, joinestimate.estimatejoin(setup, check, prereq));
		assertEquals(600000, joinestimate.estimatejoin(setup, check, TS));
		assertEquals(3000000, joinestimate.estimatejoin(setup, check, teaches));
		assertEquals(30000, joinestimate.estimatejoin(setup, check, section));
		assertEquals(900000, joinestimate.estimatejoin(setup, check, CS));
	}
	
	
	@Test
	public void estimationtest_TS() throws SQLException
	{
		String check = TS;
		assertEquals(40000, joinestimate.estimatejoin(setup, check, student));
		assertEquals(40000, joinestimate.estimatejoin(setup, check, advisor));
		assertEquals(1000, joinestimate.estimatejoin(setup, check, inst));
		assertEquals(400, joinestimate.estimatejoin(setup, check, dept));
		assertEquals(4000, joinestimate.estimatejoin(setup, check, course));
		assertEquals(2000, joinestimate.estimatejoin(setup, check, prereq));
		assertEquals(600000, joinestimate.estimatejoin(setup, check, takes));
		assertEquals(2000, joinestimate.estimatejoin(setup, check, teaches));
		assertEquals(2000, joinestimate.estimatejoin(setup, check, section));
		assertEquals(600, joinestimate.estimatejoin(setup, check, CS));
	}
	
	@Test
	public void estimationtest_teaches() throws SQLException
	{
		String check = teaches;
		assertEquals(200000, joinestimate.estimatejoin(setup, check, student));
		assertEquals(200000, joinestimate.estimatejoin(setup, check, advisor));
		assertEquals(100, joinestimate.estimatejoin(setup, check, inst));
		assertEquals(2000, joinestimate.estimatejoin(setup, check, dept));
		assertEquals(20000, joinestimate.estimatejoin(setup, check, course));
		assertEquals(10000, joinestimate.estimatejoin(setup, check, prereq));
		assertEquals(3000000, joinestimate.estimatejoin(setup, check, takes));
		assertEquals(2000, joinestimate.estimatejoin(setup, check, TS));
		assertEquals(100, joinestimate.estimatejoin(setup, check, section));
		assertEquals(3000, joinestimate.estimatejoin(setup, check, CS));
	}
	
	@Test
	public void estimationtest_section() throws SQLException
	{
		String check = section;
		
		assertEquals(200000, joinestimate.estimatejoin(setup, check, student));
		
		assertEquals(200000, joinestimate.estimatejoin(setup, check, advisor));
		assertEquals(5000, joinestimate.estimatejoin(setup, check, inst));
		assertEquals(111, joinestimate.estimatejoin(setup, check, dept));
		assertEquals(100, joinestimate.estimatejoin(setup, check, course));
		assertEquals(10000, joinestimate.estimatejoin(setup, check, prereq));
		assertEquals(30000, joinestimate.estimatejoin(setup, check, takes));
		assertEquals(2000, joinestimate.estimatejoin(setup, check, TS));
		assertEquals(100, joinestimate.estimatejoin(setup, check, teaches));
		assertEquals(100, joinestimate.estimatejoin(setup, check, CS));
	}
	
	@Test
	public void estimationtest_classroom() throws SQLException
	{
		String check = CS;
		assertEquals(60000, joinestimate.estimatejoin(setup, check, student));
		assertEquals(60000, joinestimate.estimatejoin(setup, check, advisor));
		assertEquals(1500, joinestimate.estimatejoin(setup, check, inst));
		assertEquals(600, joinestimate.estimatejoin(setup, check, dept));
		assertEquals(6000, joinestimate.estimatejoin(setup, check, course));
		assertEquals(3000, joinestimate.estimatejoin(setup, check, prereq));
		assertEquals(900000, joinestimate.estimatejoin(setup, check, takes));
		assertEquals(600, joinestimate.estimatejoin(setup, check, TS));
		assertEquals(3000, joinestimate.estimatejoin(setup, check, teaches));
		assertEquals(100, joinestimate.estimatejoin(setup, check, section));
	}
	
}
