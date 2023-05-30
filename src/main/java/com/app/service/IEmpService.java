package com.app.service;

import java.util.List;

import com.app.model.Employee;


public interface IEmpService {
	
	public boolean saveEmployee(Employee employee); //create case
	public List<Employee> getAllEmployees(); //find all - in case of fetch 
	public Employee getOneEmp(Integer id); //find by id case - edit(will return an object)
	public void deleteEmp(Integer id);  //delete case can keep return type void
	public void updateEmployee(Employee employee);
	
}
