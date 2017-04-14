package com.wesdm.springhibernate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wesdm.springhibernate.dao.EmployeeDao;
import com.wesdm.springhibernate.model.Employee;
import com.wesdm.springhibernate.model.EmployeeFactory;
import com.wesdm.springhibernate.service.EmployeeService;

public class AppMain {
	
	public static void main(String args[]) {
		AppMain main = new AppMain();
		main.runWithJpaAbstraction();
	}

	private void runWithHibernateImpl() {
		// AnnotationConfigApplicationContext context = new
		// AnnotationConfigApplicationContext();
		// context.scan("com.wesdm.springhibernate");
		// context.refresh();
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("persistenceHibernate.xml");

		EmployeeService service = (EmployeeService) context.getBean("employeeService");

		List<Employee> employees = EmployeeFactory.createEmployees();
		for(Employee e : employees) {
	        service.saveEmployee(e);
		}


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
		service.deleteEmployeeBySsn("ssn00000002");

		/*
		 * update an employee
		 */

		Employee employee = service.findBySsn("ssn00000001");
		employee.setSalary(new BigDecimal(50000));  //if transaction still existed, entity would be managed, this call to setSalary is all that's need to update
		service.updateEmployee(employee);

		/*
		 * Get all employees list from database
		 */
		List<Employee> employeeList = service.findAllEmployees();
		for (Employee emp : employeeList) {
			System.out.println(emp);
		}

		context.close();
	}
	
	private void runWithJpaAbstraction(){

	    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("persistenceJPA.xml");

 
        EmployeeService service = (EmployeeService) context.getBean("employeeService");
		EmployeeDao dao = (EmployeeDao) context.getBean("employeeJpaDao");

        
        //EntityManager em = ((EmployeeJpaDaoImpl)service.getDao()).getEntityManager();
 
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
        service.deleteEmployeeBySsn("ssn00000002");
 
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
 
        context.close();
    }
}
