package com.wesdm.springhibernate.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.joda.time.LocalDate;

import com.google.common.collect.Lists;

public class EmployeeFactory {
	 
	static Random rand = new Random();
	
	public static List<Employee> createEmployees() {
		
		List<Employee> employees = Lists.newArrayList();
		
		/*
		 * Create Employee1
		 */
		Employee employee = null;
		for(int i = 0; i < 5; i++) {
			// nextInt is normally exclusive of the top value,
		    // so add 1 to make it inclusive
		    int randomNum = rand.nextInt((12 - 1) + 1) + 1;
		    
			employee = new Employee();
			employee.setName("First Last"+i);
			employee.setJoiningDate(new LocalDate(2010, randomNum, randomNum));
			employee.setSalary(new BigDecimal((i*1)+1000));
			employee.setSsn("ssn0000000"+i);
			
			employees.add(employee);
		}

		
		return employees;
	}
}
