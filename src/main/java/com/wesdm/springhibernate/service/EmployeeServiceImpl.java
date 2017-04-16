package com.wesdm.springhibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wesdm.springhibernate.dao.EmployeeDao;
import com.wesdm.springhibernate.model.Employee;

@Service("employeeService")
@Transactional				//Mark Services as transcational as opposed to DAOs
public class EmployeeServiceImpl implements EmployeeService{
 
    @Autowired
    @Qualifier(value="employeeJpaDao")
    private EmployeeDao dao;
     
    public void saveEmployee(Employee employee) {
        dao.save(employee);
    }
 
    public List<Employee> findAllEmployees() {
        return dao.findAll();
    }
 
    public void deleteEmployeeBySsn(String ssn) {
        dao.deleteEmployeeBySsn(ssn);
    }
 
    public Employee findBySsn(String ssn) {
        return dao.findBySsn(ssn);
    }
    
    
    public void updateEmployee(Employee employee){
        dao.updateEmployee(employee);
    }

	@Override
	public Employee findEm() {
		return dao.findEm();
	}

	@Override
	public Employee getReference(int id) {
		return dao.getReference(id);
	}

	@Override
	public void delete(long id) {
		dao.delete(id);
	}
    
}