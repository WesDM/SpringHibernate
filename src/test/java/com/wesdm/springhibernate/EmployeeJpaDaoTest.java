package com.wesdm.springhibernate;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wesdm.springhibernate.dao.EmployeeDao;
import com.wesdm.springhibernate.model.Employee;
import com.wesdm.springhibernate.model.EmployeeFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ JpaTestConfig.class })
@ActiveProfiles("dev")		//run test with dev profile beans
public class EmployeeJpaDaoTest {
	
	static final Logger LOG = LoggerFactory.getLogger(EmployeeJpaDaoTest.class);
	
	@Autowired
	public EmployeeDao employeeJpaDao;

	@Test
	@Transactional
	@Rollback(true)
	public void saveTest(){
		List<Employee> employees = EmployeeFactory.createEmployees();  //transient state
		for(Employee e : employees) {
			employeeJpaDao.save(e);										//persistent state - associated with persistence context, in db when flushed
		}
		List<Employee> employeess = employeeJpaDao.findAll();
		Assert.assertEquals(employees.get(0).getName(),employeess.get(0).getName());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void removeTest(){
		List<Employee> employees = EmployeeFactory.createEmployees();  //transient state
		for(Employee e : employees) {
			employeeJpaDao.save(e);										//persistent state - associated with persistence context, in db when flushed
		}
		List<Employee> employeess = employeeJpaDao.findAll();
		Assert.assertEquals(employees.get(0).getName(),employeess.get(0).getName());
		
		//employeeJpaDao.delete(employees.get(1).getId());
		
		employeeJpaDao.delete(employees.get(1));
		employeess = employeeJpaDao.findAll();

		LOG.info(Arrays.toString(employeess.toArray()));
	}
}
