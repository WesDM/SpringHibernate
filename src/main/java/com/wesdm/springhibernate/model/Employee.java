package com.wesdm.springhibernate.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SelectBeforeUpdate;
import org.joda.time.LocalDate;

/**
 * @author Wesley
 *
 */
@Entity
@Table(name = "EMPLOYEES") // Map to different table name
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
	@SequenceGenerator(name = "sequence_generator", sequenceName = "employee_seq", allocationSize = 5)
	private Long id;

	@NotNull
	@Column(name = "NAME")
	private String name;

	@NotNull
	@Column(name = "JOINING_DATE")
	@org.hibernate.annotations.Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate joiningDate;

	@NotNull
	@Column(name = "SALARY")
	private BigDecimal salary;

	@NotNull
	@Column(name = "SSN", unique = true)
	@org.hibernate.annotations.NaturalId
	private String ssn;

//	private Address homeAddress;
//
//	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "BILLING_STREET")),
//			@AttributeOverride(name = "zipcode", column = @Column(name = "BILLING_ZIPCODE", length = 5)),
//			@AttributeOverride(name = "city", column = @Column(name = "BILLING_CITY")) })
//	protected Address billingAddress;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON", updatable = false)
	@org.hibernate.annotations.CreationTimestamp
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_ON")
	@org.hibernate.annotations.UpdateTimestamp
	private Date updated;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "COMPANY_ID")  //Foreign Key
//	private Company company;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

//	public Company getCompany() {
//		return company;
//	}
//
//	public void setCompany(Company company) {
//		this.company = company;
//	}
//	
//	public Address getBillingAddress() {
//		return billingAddress;
//	}
//
//	public Address getHomeAddress() {
//		return homeAddress;
//	}
//
//	public void setHomeAddress(Address homeAddress) {
//		this.homeAddress = homeAddress;
//	}

	/*
	 * Since instances of employee will be added to a set in detached state, must implement equals and hashcode
	 */

	/**
	 * Generates hash code
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.ssn); // compact way to generate hashcode jdk 7+
	}

	/**
	 * Compares this object to another
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(this.ssn, other.ssn); // compact way to generate equals comparison jdk 7+
		// Objects.equals(this.id, other.id)
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", joiningDate=" + joiningDate + ", salary=" + salary + ", ssn=" + ssn + "]";
	}
}
