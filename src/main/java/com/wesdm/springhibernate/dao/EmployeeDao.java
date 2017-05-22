package com.wesdm.springhibernate.dao;

import java.math.BigDecimal;

import com.wesdm.springhibernate.model.Employee;

public interface EmployeeDao extends BasicDao<Employee> {

	int deleteEmployeeBySsn(String ssn);

	Employee findBySsn(String ssn);

	Employee getReference(long id);

	void updateEmployee(Employee employee);
	
	void updateSalaryBySsn(String ssn, BigDecimal salary);

	Employee findEm();
	
	void delete(Employee e);

}
