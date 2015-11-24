package com.zensar.zendirectory.pojo;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="address")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressPOJO implements Serializable {
	private Long id;
	private String city;
	private String state;
	@XmlElement(name="employee")
	private EmployeePOJO employee;

	public AddressPOJO() {

	}

	public AddressPOJO(String city, String state) {
		super();
		this.city = city;
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public EmployeePOJO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeePOJO employee) {
		this.employee = employee;
	}

}
