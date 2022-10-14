package com.global.hr.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.Employee;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee,Long>{
	
	List<Employee> findByName(String name);
	List<Employee> findByNameContainingAndSalaryGreaterThanEqual(String name,Double salary);

	@Query("select * from employees where name like :empName and salary >= :empSalary")
	List<Employee> findByNameAndSalary(@Param("empName") String name,
			@Param("empSalary") Double salary);
	
}
