package com.global.hr.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeRepo;
import com.global.hr.service.EmployeeService;

@RestController
//@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	Logger log = LoggerFactory.getLogger(Employee.class);
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/count")
	public long countEmp() {
		return employeeService.countEmp();
	}
	
	@GetMapping("/{id}")
	//@ResponseBody
	// or public ResponseEntity<?> findById(@PathVariable Long id)
	public ResponseEntity<Employee> findById(@PathVariable Long id,
			@RequestHeader("accept-language") String acceptLanguage) {
		log.info("Accept Language : " + acceptLanguage);
		//return new ResponseEntity(employeeRepo.findById(id).get(),HttpStatus.OK);
		return ResponseEntity.ok(employeeService.findById(id));
	}
	
	@GetMapping("")
	public List<Employee> findAll() {
		return (List<Employee>) employeeService.findAll();
	}
	
	@PostMapping("")
	public Employee addEmp(@RequestBody Employee employee) {
		return employeeService.addEmp(employee);
	}
	
	@PutMapping("")
	public Employee updateEmp(@RequestBody Employee employee) {
		return employeeService.updateEmp(employee);
	}
	
	@DeleteMapping("/{empId}")
	public void deleteById(@PathVariable(name="empId") Long id) {
		employeeService.deleteById(id);
	}
	
	@GetMapping("filter/{name}/{salary}")
	//public List<Employee> findByNameAndSalary(@RequestParam String name,@RequestParam Double salary) {
	public List<Employee> findByNameAndSalary(@PathVariable String name,@PathVariable Double salary) {
		return employeeService.findByNameAndSalary(name,salary);
	}
	
	@PutMapping("/salary")
	public int updateSalary(@RequestParam Long id , @RequestParam Double salary) {
		return employeeService.updateSalary(id, salary);
	}
	public void testJackson() throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "{\r\n"
				+ "  \"employeeId\": 13,\r\n"
				+ "  \"name\": \"name3\",\r\n"
				+ "  \"salary\": 2450.57,\r\n"
				+ "  \"active\": false\r\n"
				+ "}";
		// json to object  conversion
		Employee emp = mapper.readValue(jsonString,Employee.class);
		
		// object to json
		String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp);
	}
	
}
