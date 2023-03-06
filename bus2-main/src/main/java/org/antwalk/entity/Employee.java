package org.antwalk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@GeneratedValue
	@Column(name="eid")
	private long eid;
	
	@Column(name="name")
	private String name;
	
	@Column(name="contact_no")
	private String contactNo;
	
	@Column(name="username")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bus_id", referencedColumnName = "bid")
	private Bus b;

	public long getEid() {
		return eid;
	}

	public void setEid(long eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Bus getB() {
		return b;
	}

	public void setB(Bus b) {
		this.b = b;
	}

	public Employee(long eid, String name, String contactNo, String email, String password, Bus b) {
		super();
		this.eid = eid;
		this.name = name;
		this.contactNo = contactNo;
		this.email = email;
		this.password = password;
		this.b = b;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
