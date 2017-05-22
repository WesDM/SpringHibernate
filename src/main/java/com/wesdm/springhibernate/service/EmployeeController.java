package com.wesdm.springhibernate.service;

import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	@CoolService
	public EmployeeController(EmployeeService service ){
		this.employeeService = service;
	}
}
