package com.zensar.zendirectory.dao;

import java.sql.SQLException;
import java.util.List;

import com.zensar.zendirectory.entity.AddressEntity;
import com.zensar.zendirectory.entity.EmployeeEntity;

public interface EmployeeDao
{

		EmployeeEntity getEmployeeByID(long id) throws SQLException;
		List<EmployeeEntity> getEmployeeByName(String empName) throws SQLException;
		List<AddressEntity> getEmployeeByAddress(String city) throws SQLException;
		void insertPerson();
}
