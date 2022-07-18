package com.my.employeeproject.repository;

import java.util.List;
import com.my.employeeproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	List<Employee> findByFirstNameContainsAllIgnoreCase(String firstName);
	List<Employee> findAllByOrderByFirstNameAsc();

}


