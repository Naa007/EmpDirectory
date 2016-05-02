package com.zensar.zendirectory.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.zensar.zendirectory.dao.EmployeeDao;
import com.zensar.zendirectory.entity.AddressEntity;
import com.zensar.zendirectory.entity.EmployeeEntity;
import com.zensar.zendirectory.pojo.AddressPOJO;
import com.zensar.zendirectory.pojo.EmployeePOJO;
import com.zensar.zendirectory.util.ApplicationContextUtil;

@Path("/query")
@Component
public class RequestHandler {
	
	private ApplicationContext context = ApplicationContextUtil.getApplicationContext();

	public RequestHandler(){
	}

	@GET
	@Path("/empId")
	@Produces({"application/xml", "application/json"})
	public EmployeePOJO getEmployeeByID(@QueryParam("empID") long empID) {
		try {
			EmployeeDao employeeDao = (EmployeeDao)context.getBean("EmployeeDaoImpl");
			EmployeeEntity entity = employeeDao.getEmployeeByID(empID);
			return employeeEntityToPojo(entity);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("GIT test");
			return null;
		}
	}
	
	@GET
	@Path("/address")
	@Produces({"application/xml", "application/json"})
	public List<AddressPOJO> getEmployeeByAddress(@QueryParam("city") String city) {
		try {
			List<AddressEntity> entities  = new ArrayList<AddressEntity>();
			EmployeeDao employeeDao = (EmployeeDao)context.getBean("EmployeeDaoImpl");
			entities = employeeDao.getEmployeeByAddress(city);
			return addressListEntityToPojo(entities);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path("/name")
	@Produces({"application/xml", "application/json"})
	public List<EmployeePOJO> getEmployeeByName(@QueryParam("name") String name) {
		try {
			List<EmployeeEntity> entities  = new ArrayList<EmployeeEntity>();
			EmployeeDao employeeDao = (EmployeeDao)context.getBean("EmployeeDaoImpl");
			entities = employeeDao.getEmployeeByName(name);
			return employeeListEntityToPojo(entities);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private List<EmployeePOJO> employeeListEntityToPojo(
			List<EmployeeEntity> entities) {
		List<EmployeePOJO> pojos = new ArrayList<EmployeePOJO>();
		Iterator<EmployeeEntity> iterator = entities.iterator();
		while(iterator.hasNext()){
			EmployeeEntity entity = iterator.next();
			EmployeePOJO pojo = new EmployeePOJO();
			pojo.setId(entity.getId());
			pojo.setName(entity.getName());
			pojo.setAddresses(addressEntityToPojoWithoutEmployee(entity.getAddresses()));
			pojos.add(pojo);
		}
		return pojos;
	}

	private Set<AddressPOJO> addressEntityToPojoWithoutEmployee(
			Set<AddressEntity> addresses) {
		Set<AddressPOJO> pojos = new HashSet<AddressPOJO>();
		Iterator<AddressEntity> iterator = addresses.iterator();
		while(iterator.hasNext()){
			AddressEntity addressEntity = iterator.next();
			AddressPOJO addressPojo = new AddressPOJO();
			addressPojo.setId(addressEntity.getId());
			addressPojo.setCity(addressEntity.getCity());
			addressPojo.setState(addressEntity.getState());
			pojos.add(addressPojo);
		}
		return pojos;
	}

	private List<AddressPOJO> addressListEntityToPojo(
			List<AddressEntity> entities) {
		List<AddressPOJO> pojos  = new ArrayList<AddressPOJO>();
		Iterator<AddressEntity> iterator  = entities.iterator();
		while(iterator.hasNext()){
			AddressEntity entity = iterator.next();
			AddressPOJO pojo = new AddressPOJO();
			pojo.setId(entity.getId());
			pojo.setCity(entity.getCity());
			pojo.setState(entity.getState());
			pojo.setEmployee(employeeEntityToPojoWithoutAddress(entity.getEmployee()));
			pojos.add(pojo);
		}
		return pojos;
	}

	private EmployeePOJO employeeEntityToPojoWithoutAddress(
			EmployeeEntity entity) {
		EmployeePOJO pojo = new EmployeePOJO();
		pojo.setId(entity.getId());
		pojo.setName(entity.getName());
		return pojo;
	}

	private EmployeePOJO employeeEntityToPojo(EmployeeEntity entity) {
		EmployeePOJO pojo = new EmployeePOJO();
		pojo.setId(entity.getId());
		pojo.setName(entity.getName());
		Set<AddressPOJO> addresses = new HashSet<AddressPOJO>();
		Iterator<AddressEntity> iterator = entity.getAddresses().iterator();
		while(iterator.hasNext()){
			AddressEntity addressEntity = iterator.next();
			AddressPOJO addressPojo = new AddressPOJO();
			addressPojo.setId(addressEntity.getId());
			addressPojo.setCity(addressEntity.getCity());
			addressPojo.setState(addressEntity.getState());
			addresses.add(addressPojo);
		}
		pojo.setAddresses(addresses);
		return pojo;
	}

	@GET
	@Path("/insert")
	public void insertPerson(){
		System.out.println("insert");
		EmployeeDao employeeDao = (EmployeeDao)context.getBean("EmployeeDaoImpl");
		employeeDao.insertPerson();
	}
}