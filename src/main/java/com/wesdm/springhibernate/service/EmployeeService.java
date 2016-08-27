package com.wesdm.springhibernate.service;

import java.util.List;

import com.wesdm.springhibernate.dao.EmployeeDao;
import com.wesdm.springhibernate.model.Employee;

public interface EmployeeService {
    void saveEmployee(Employee employee);
    
    List<Employee> findAllEmployees();
 
    void deleteEmployeeBySsn(String ssn);
 
    Employee findBySsn(String ssn);
    
    Employee getReference(int id);
    
    void updateEmployee(Employee employee);
    
    Employee findEm();
    
}
