package com.zensar.zendirectory.daoimpl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zensar.zendirectory.dao.EmployeeDao;
import com.zensar.zendirectory.entity.AddressEntity;
import com.zensar.zendirectory.entity.EmployeeEntity;

@Repository("EmployeeDaoImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class EmployeeDaoImpl implements EmployeeDao
{
		@PersistenceContext
		private EntityManager entityManager;

	    public EntityManager getEntityManager()
	    {
			    return entityManager;
	    }

	    public void setEntityManager(EntityManager entityManager)
	    {
			    this.entityManager = entityManager;
	    }

	    @Override
	    public EmployeeEntity  getEmployeeByID(long id)
	    {
	    	EmployeeEntity  employee = entityManager.find(EmployeeEntity.class, id);
	    	return employee;
	    }

		@Override
		public List<EmployeeEntity> getEmployeeByName(String empName) throws SQLException {
			List<EmployeeEntity> employees = new ArrayList<EmployeeEntity>(); 
			Query query = entityManager.createQuery("FROM EmployeeEntity e where e.name = " + empName);
			for (Object result : query.getResultList()) {
	            employees.add((EmployeeEntity) result);
	        }
			System.out.println(employees);
			return employees;
		}


		@Override
		public List<AddressEntity> getEmployeeByAddress(String city)
				throws SQLException {
			List<AddressEntity> addresses = new ArrayList<AddressEntity>();
			Query query = entityManager.createQuery("FROM AddressEntity a where a.city = " + city);
			for (Object result : query.getResultList()) {
	            addresses.add((AddressEntity) result);
	        }
			System.out.println(addresses);
			return addresses;
		}
		
		@Override
		public void insertPerson() {
			EmployeeEntity employee = new EmployeeEntity();
			employee.setName("raam");
			
			AddressEntity address = new AddressEntity();
			address.setCity("pune");
			address.setState("MH");
			
			AddressEntity address2 = new AddressEntity();
			address2.setCity("nagpur");
			address2.setState("MH");
			
			employee.addAddress(address);
			employee.addAddress(address2);
			entityManager.persist(employee);
			System.out.println("check db");
		}
}
