package com.global.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeRepo;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	
	@GetMapping("/count")
	public long countEmp() {
		return employeeRepo.count();
	}
	
	@GetMapping("/{id}")
	public Employee findById(@PathVariable Long id) {
		return employeeRepo.findById(id).get();
	}
	
	@GetMapping("")
	public List<Employee> findAll() {
		return (List<Employee>) employeeRepo.findAll();
	}
	
	@PostMapping("insert")
	public Employee addEmp(@RequestBody Employee employee) {
		return employeeRepo.save(employee);
	}
	@PutMapping("update")
	public Employee updateEmp(@RequestBody Employee employee) {
		return employeeRepo.save(employee);
	}

	@GetMapping("filter/{name}/{salary}")
	public List<Employee> findByNameAndSalary(@PathVariable String name,@PathVariable Double salary) {
		return employeeRepo.findByNameAndSalary(name,salary);
	}
}
