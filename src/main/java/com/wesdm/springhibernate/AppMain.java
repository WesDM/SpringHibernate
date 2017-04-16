package com.wesdm.springhibernate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wesdm.springhibernate.dao.EmployeeDao;
import com.wesdm.springhibernate.dao.jpa.EmployeeJpaDaoImpl;
import com.wesdm.springhibernate.model.Employee;
import com.wesdm.springhibernate.model.EmployeeFactory;
import com.wesdm.springhibernate.service.EmployeeService;

public class AppMain {
	
	public static void main(String args[]) {
		AppMain main = new AppMain();
		main.runWithJpaAbstraction();
	}
	
	/**
	 * Nonrecommended approach using Hibernate session factory directly to access DB
	 */
	private void runWithHibernateImpl() {
		// AnnotationConfigApplicationContext context = new
		// AnnotationConfigApplicationContext();
		// context.scan("com.wesdm.springhibernate");
		// context.refresh();
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("persistenceHibernate.xml");

		EmployeeService service = (EmployeeService) context.getBean("employeeService");

		danceWithDB(service);

		context.close();
	}
	
	/**
	 * Recommended approach using JPA's entity manager to connect to DB. Can access Hibernates Session Factory
	 * if need be from Entity Manager unwrap(Session.class) method.
	 */
	private void runWithJpaAbstraction(){

	    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("persistenceJPA.xml");

 
        EmployeeService service = (EmployeeService) context.getBean("employeeService");
        
        //EntityManager em = ((EmployeeJpaDaoImpl)service.getDao()).getEntityManager();
 
		danceWithDB(service);
 
        context.close();
    }

	private void danceWithDB(EmployeeService service) {
		List<Employee> employees = EmployeeFactory.createEmployees();
		for(Employee e : employees) {
	        service.saveEmployee(e);
		}
        
        service.findEm();
         
        /*
         * Get all employees list from database
         */
        employees = service.findAllEmployees();
        for (Employee emp : employees) {
            System.out.println(emp);
        }
 
        /*
         * delete an employee
         */
        service.delete(employees.get(0).getId());
        //service.deleteEmployeeBySsn("ssn00000002");
 
        /*
         * update an employee. to associate foreign keys prefer getReference()
         */
 
        Employee employee = service.findBySsn("ssn00000001");
        employee.setSalary(new BigDecimal(50000));
        service.updateEmployee(employee);
        //dao.updateSalaryBySsn("1", new BigDecimal(50000));
        Collections.emptyList();
 
        /*
         * Get all employees list from database
         */
        List<Employee> employeeList = service.findAllEmployees();
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }
	}
}
