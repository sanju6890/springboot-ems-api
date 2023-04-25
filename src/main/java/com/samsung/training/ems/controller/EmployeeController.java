package com.samsung.training.ems.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samsung.training.ems.exception.ResourceNotFound;
import com.samsung.training.ems.model.Employee;
import com.samsung.training.ems.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired(required=false)
	private EmployeeRepository employeeRepository;

	// REST API to get all the employees details
	@GetMapping("/list/employee")
	public List<Employee> getAllEmployeesDetails() {
		return employeeRepository.findAll();
	}

	// REST API to create the employee details
	@PostMapping("/add/employee")
	public Employee createEmployee(@RequestBody Employee emp) {
		return employeeRepository.save(emp);			
	}

	// REST API to get the employee details by Id
	@GetMapping("/get/employee/{id}")
	public ResponseEntity<Employee> getEmployeesById(@PathVariable Long id) {
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(()->new ResourceNotFound("Employee data does not exist with id: "+id));
		return ResponseEntity.ok(emp);
	}

	// REST API to update the employee details by Id
	@PutMapping("/update/employee/{id}")
	public ResponseEntity<Employee> updateEmployeesById(@PathVariable Long id, @RequestBody Employee empDetails) {
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(()->new ResourceNotFound("Employee data does not exist with id: "+id));
		emp.setEmailId(empDetails.getEmailId());
		emp.setfName(empDetails.getfName());
		emp.setlName(empDetails.getlName());
		Employee updateEmployee=employeeRepository.save(emp);
		return ResponseEntity.ok(updateEmployee);
	}

	// REST API to delete the employee details by Id
	@DeleteMapping("/delete/employee/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Employee data does not exist with id:" + id));

		employeeRepository.delete(emp);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
