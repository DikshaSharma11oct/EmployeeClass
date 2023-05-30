package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Employee;
import com.app.repository.EmpRepository;

@Service
public class EmpServiceImpl implements IEmpService {

	@Autowired
	private EmpRepository empRepo;

	@Override
	public boolean saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return empRepo.save(employee)!=null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return empRepo.findAll();
	}

	@Override
	public Employee getOneEmp(Integer id) {
		// TODO Auto-generated method stub
		Employee employee = empRepo.findById(id).orElseThrow();
		return employee;
	}

	@Override
	public void deleteEmp(Integer id) { 
		// TODO Auto-generated method stub
		Employee emp=getOneEmp(id);
		empRepo.delete(emp);
	}


	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		empRepo.save(employee);
	}
}
