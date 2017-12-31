package com.wesdm.springhibernate.service;

import java.util.List;

import com.wesdm.springhibernate.model.Employee;

public interface EmployeeService {
    void saveEmployee(Employee employee);
    
    List<Employee> findAllEmployees();
    
    void delete(long id);
 
    void deleteEmployeeBySsn(String ssn);
 
    Employee findBySsn(String ssn);
    
    Employee getReference(Long id);
    
    void updateEmployee(Employee employee);
    
    Employee findEm();
    
}
