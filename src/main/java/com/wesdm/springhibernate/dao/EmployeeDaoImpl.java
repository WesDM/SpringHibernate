package com.wesdm.springhibernate.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.wesdm.springhibernate.model.Employee;

/**
 * This class demonstrates using Hibernate's Session Factory to instantiate a Session object which is uses to connect
 * to the database.  THIS IS NOT THE RECOMMENDED APPROACH as it uses Hibernate specific functionality leading to portability
 * issues (this doesn't happen often but still a selling point to use JPA specific functionality only).  See @see EmployeeJpaDaoImpl
 * for the recommended approach.
 * 
 * @author Wesley
 *
 */
@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao implements EmployeeDao{
 
    public void saveEmployee(Employee employee) {
        persist(employee);
    }
 
    @SuppressWarnings("unchecked")
    public List<Employee> findAllEmployees() {
        Criteria criteria = getSession().createCriteria(Employee.class);
        return (List<Employee>) criteria.list();
    }
 
    public int deleteEmployeeBySsn(String ssn) {
        Query query = getSession().createSQLQuery("delete from Employee where ssn = :ssn");
        query.setString("ssn", ssn);
        return query.executeUpdate();
    }
 
     
    public Employee findBySsn(String ssn){
        Criteria criteria = getSession().createCriteria(Employee.class);
        criteria.add(Restrictions.eq("ssn",ssn));
        return (Employee) criteria.uniqueResult();
    }
     
    public void updateEmployee(Employee employee){
        getSession().update(employee);
    }

	@Override
	public Employee findEm() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void updateSalary(Long id, BigDecimal salary) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Employee obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getReference(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Employee e) {
		// TODO Auto-generated method stub
		
	}
     
}