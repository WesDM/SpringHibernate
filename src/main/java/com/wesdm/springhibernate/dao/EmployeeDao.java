package com.wesdm.springhibernate.dao;

import java.math.BigDecimal;

import com.wesdm.springhibernate.model.Employee;

public interface EmployeeDao extends BasicDao<Employee> {

	int deleteEmployeeBySsn(String ssn);

	Employee findBySsn(String ssn);

	Employee getReference(Long id);
	
	void updateSalary(Long id, BigDecimal salary);

	Employee findEm();
	
	void delete(Employee e);

	void updateEmployee(Employee employee);

}
