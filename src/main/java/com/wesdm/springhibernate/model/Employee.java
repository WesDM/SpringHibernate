package com.wesdm.springhibernate.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

/**
 * @author Wesley
 *
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hilo_sequence_generator")
	@GenericGenerator(name = "hilo_sequence_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "hilo_seqeunce"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "3"),
			@Parameter(name = "optimizer", value = "hilo") })
	private int id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "JOINING_DATE", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate joiningDate;

	@Column(name = "SALARY", nullable = false)
	private BigDecimal salary;

	@Column(name = "SSN", unique = true, nullable = false)
	private String ssn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	/**
	 * Generates hash code
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.ssn);		//compact way to generate hashcode jdk 7+
	}
	
	/**
	 * Compares this object to another
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(this.id, other.id) &&
				Objects.equals(this.ssn, other.ssn);	//compact way to generate equals comparison jdk 7+
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", joiningDate=" + joiningDate + ", salary=" + salary
				+ ", ssn=" + ssn + "]";
	}
}
