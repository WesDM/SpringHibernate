package com.wesdm.springhibernate.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wesdm.springhibernate.dao.EmployeeDao;
import com.wesdm.springhibernate.model.Employee;

@Service("employeeService")
@Transactional				//Mark Services as transcational as opposed to DAOs
@CoolService				//Custom qualifier example for DI
public class EmployeeServiceImpl implements EmployeeService{
 
//    @Autowired						matches by type, qualifier, and then name
//    @Qualifier(value="employeeJpaDao")
//    private EmployeeDao dao;
    
    @Resource(name="employeeJpaDao")  //prefer if injecting by name. resource matches by name, type, and then qualifier
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
	public Employee getReference(Long id) {
		return dao.getReference(id);
	}

	@Override
	public void delete(long id) {
		dao.delete(id);
	}
    
}