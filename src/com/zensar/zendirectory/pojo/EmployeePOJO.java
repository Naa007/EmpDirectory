package com.zensar.zendirectory.pojo;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model class for Employee
 */
@XmlRootElement(name="employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeePOJO implements Serializable {
	private Long id;
	private String name;
	@XmlElementWrapper
	@XmlElement(name="addresses")
	private Set<AddressPOJO> addresses = new HashSet<AddressPOJO>();

	
	public EmployeePOJO(Long id, String name, Set<AddressPOJO> addresses) {
		super();
		this.id = id;
		this.name = name;
		this.addresses = addresses;
	}
	public EmployeePOJO(String name, Set<AddressPOJO> addresses) {
		super();
		this.name = name;
		this.addresses = addresses;
	}
	public EmployeePOJO() {
	}

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

	public Set<AddressPOJO> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<AddressPOJO> addresses) {
		this.addresses = addresses;
	}
	
	public void addAddress(AddressPOJO address) {
		address.setEmployee(this);
		this.addresses.add(address);
	}
}