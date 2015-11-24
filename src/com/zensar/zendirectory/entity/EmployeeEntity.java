package com.zensar.zendirectory.entity;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Model class for Employee
 */
@Entity
@Table(name="Employee"
  , uniqueConstraints = {
  @UniqueConstraint(columnNames = "ID") })

public class EmployeeEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "employee")
	private Set<AddressEntity> addresses = new HashSet<AddressEntity>();

	public EmployeeEntity(String name, Set<AddressEntity> addresses) {
		super();
		this.name = name;
		this.addresses = addresses;
	}

	public EmployeeEntity() {
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

	public Set<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<AddressEntity> addresses) {
		this.addresses = addresses;
	}
	
	public void addAddress(AddressEntity address) {
		address.setEmployee(this);
		this.addresses.add(address);
	}
}