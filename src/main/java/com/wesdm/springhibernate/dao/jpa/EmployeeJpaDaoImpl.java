package com.wesdm.springhibernate.dao.jpa;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wesdm.springhibernate.dao.EmployeeDao;
import com.wesdm.springhibernate.model.Employee;

@Repository
@Qualifier("employeeJpaDao")
public class EmployeeJpaDaoImpl extends AbstractJpaDao implements EmployeeDao {
	
	//used for lowel level sql, BATCH UPDATES, not portable, but more powerful than jpa's entity manager's create (native) query methods
	//need to merge detached object back into persistence context after updating objects with this
	@Autowired(required=true) //required attribute is preferred over @Required, default is true
	JdbcTemplate jdbcTemplate;
	
	/**
	 * Called after bean constructed
	 */
	@PostConstruct
	public void init(){
		
	}
	
	/**
	 * Called before container containing it is closed
	 */
	@PreDestroy
	public void destroy(){
		
	}

	@Override
	public void save(Employee employee) {
		persist(employee);
	}

	@Override
	public List<Employee> findAll() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> rootEntry = cq.from(Employee.class);
		CriteriaQuery<Employee> all = cq.select(rootEntry);
		TypedQuery<Employee> allQuery = getEntityManager().createQuery(all);
		return allQuery.getResultList();
	}
	
	@Override
	public int deleteEmployeeBySsn(String ssn) {
		Query query = getEntityManager().createNativeQuery("DELETE FROM Employee e WHERE e.ssn = :ssn"); //won't delete child refs if any exist, must manually do
		query.setParameter("ssn", ssn);
		return query.executeUpdate();
	}

	@Override
	public Employee findBySsn(String ssn) {
		TypedQuery<Employee> query = getEntityManager().createQuery("SELECT e FROM Employee e WHERE e.ssn = :ssn",Employee.class);
		query.setParameter("ssn", ssn);
		return query.getSingleResult();
	}

	@Override
	public void updateEmployee(Employee employee) {
		getEntityManager().merge(employee);
	}
	
	
	public Employee findEm(){
		Employee actor = jdbcTemplate.queryForObject(
		        "select * from Employee where ssn = ?",
		        new Object[]{"ssn00000002"},
		        new RowMapper<Employee>() {
		            public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	Employee actor = new Employee();
		                actor.setName(rs.getString("NAME"));
		                actor.setJoiningDate(LocalDate.fromDateFields(rs.getDate("JOINING_DATE")));
		                actor.setSalary(rs.getBigDecimal("SALARY"));
		                actor.setSsn(rs.getString("SSN"));
		                return actor;
		            }
		        });
		System.out.println("JDBCTEMPLATE:  "+actor);
		return actor;
	}

	@Override
	public Employee getReference(long id) {
		return getEntityManager().getReference(Employee.class, id);
	}

	@Override
	public void updateSalaryBySsn(String ssn, BigDecimal salary) {
		Employee e = getReference(Integer.valueOf(ssn));
		e.setSalary(salary);
	}

	@Override
	public void delete(long id) {
		delete(getReference(id));		
	}
}
