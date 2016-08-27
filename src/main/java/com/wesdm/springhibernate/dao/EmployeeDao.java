package com.wesdm.springhibernate.dao;

import java.math.BigDecimal;
import java.util.List;

import com.wesdm.springhibernate.model.Employee;

public interface EmployeeDao {
	void saveEmployee(Employee employee);

	List<Employee> findAllEmployees();

	int deleteEmployeeBySsn(String ssn);

	Employee findBySsn(String ssn);

	Employee getReference(int id);

	void updateEmployee(Employee employee);
	
	void updateSalaryBySsn(String ssn, BigDecimal salary);

	Employee findEm();
}
