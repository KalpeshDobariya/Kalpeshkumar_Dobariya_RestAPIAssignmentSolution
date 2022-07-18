package com.my.employeeproject.controller;

import java.util.Collection;
import java.util.List;
import com.my.employeeproject.entity.Employee;
import com.my.employeeproject.entity.Role;
import com.my.employeeproject.entity.User;
import com.my.employeeproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Employee Managment system";  }
	
	
	@Autowired
	public EmployeeService employeeService;
	
	
	public EmployeeRestController(EmployeeService theEmployeeService){
		employeeService = theEmployeeService;}
	
	// To update user 
	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return employeeService.saveUser(user);  }
	
	// To update role
	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) {
		return employeeService.saveRole(role);  }
	

	// To list all the employee detail
	@GetMapping("/employees")
	public List<Employee> findAll() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipal = authentication.getAuthorities();
		System.out.println(currentPrincipal);
		return employeeService.findAll();}
	

	// GET employees by employee Id
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Employee theEmployee = employeeService.findById(employeeId);
		if (theEmployee == null) {
			throw new RuntimeException("Sorry! Employee ID = "+ employeeId + "not found, Please enter the valid Employee ID.");
		}
		return theEmployee;}
	

	
	// POST to add new employee
	@PostMapping("/employees")
	public Employee add(@RequestBody Employee theEmployee) {
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;}
	

	
	// PUT to update existing employee detail
	@PutMapping("/employees")
	public Employee update(@RequestBody Employee theEmployee) {
		employeeService.save(theEmployee);
		return theEmployee;}
	
	

	// DELETE to delete employee
	@DeleteMapping("/employees/{employeeId}")
	public String delete(@PathVariable int employeeId) {
		Employee tempEmployee = employeeService.findById(employeeId);

		// If ID not found 
		if (tempEmployee == null) {
			throw new RuntimeException("Sorry! Employee ID  = "+ employeeId + "not found, Please enter the valid Employee ID." );
		} 
		employeeService.deleteById(employeeId);
		return "Deleted employee id - " + employeeId;}
	
	
	// Search employee by first name
	@GetMapping("/employees/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable String firstName) {
		return employeeService.searchByFirstName(firstName);}
	
	
	// list employee in ascending order
	@GetMapping("/employees/sort-asc")
	public List<Employee> sortByFirstName() {
		return employeeService.sortByFirstNameAsc();}
	

}
