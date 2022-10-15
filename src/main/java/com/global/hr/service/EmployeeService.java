package com.global.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	public long countEmp() {
		return employeeRepo.count();
	}
	
	public List<Employee> findByName(String name){
		return employeeRepo.findByName(name);
	}
	
	public List<Employee> findByNameContainingAndSalaryGreaterThanEqual(String name,Double salary){
		return employeeRepo.findByNameContainingAndSalaryGreaterThanEqual(name, salary);
	}
	
	public Employee addEmp(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public Employee updateEmp( Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public void deleteById( Long id) {
		 employeeRepo.deleteById(id);
	}
	
	public Employee findById( Long id) {
		//return new ResponseEntity(employeeRepo.findById(id).get(),HttpStatus.OK);
		return employeeRepo.findById(id).get();
	}
	
	public List<Employee> findAll() {
		return (List<Employee>) employeeRepo.findAll();
	}
	
	public List<Employee> findByNameAndSalary(String name,Double salary) {
		return employeeRepo.findByNameAndSalary(name,salary);
	}
	public int updateSalary(Long id , Double salary) {
		return employeeRepo.updateSalary(id, salary);
	}

}
