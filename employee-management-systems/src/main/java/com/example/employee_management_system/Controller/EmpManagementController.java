package com.example.employee_management_system.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.example.employee_management_system.Pojo.EmployeePojo;
import com.example.employee_management_system.Service.ManagementService;

@RestController
@RequestMapping("/employee")
public class EmpManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmpManagementController.class);
	
	@Autowired
	ManagementService managementService;
	
	@PostMapping
	public String createEmployee(@RequestBody EmployeePojo employeePojo) {
		logger.info("Request to create Employee: {}", employeePojo);
		return managementService.AddEmployee(employeePojo);
	}
	
	
	@GetMapping
	public List<EmployeePojo> getAllEmployee(){
		logger.info("Request to fetch all employees");
		return managementService.getEmployees();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeePojo> getEmployeeByID(@PathVariable int id) {
		logger.info("Request to fetch employee by ID: {}", id);
		EmployeePojo employeeByID = managementService.getEmployeeByID(id);
		 if(employeeByID != null) {
			 return ResponseEntity.ok(employeeByID);
		 }else {
			 return ResponseEntity.status(404).build();
		 }
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateEmployee(@RequestBody EmployeePojo employeePojo , @PathVariable Integer id) {
		logger.info("Request to update Employee ID: {}", id);
		return managementService.updateEmployee(employeePojo, id);
	}
	
	
	
	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable int id) {
		logger.info("Request to delete Employee ID: {}", id);
		return managementService.deleteEmployee(id);
	}
	
	
	@GetMapping("/name/{name}")
	public ResponseEntity<EmployeePojo> getElementbyName(@PathVariable String name) {
		logger.info("Request to fetch employee by Name: {}", name); 
		EmployeePojo elementByName = managementService.getElementByName(name);
		 if(elementByName != null) {
			 return ResponseEntity.ok(elementByName);
		 }else {
			 return ResponseEntity.status(404).build();
		 }
	}
	
	
	@GetMapping("/department/{department}")
	public List<EmployeePojo> getElementbyDepart(@PathVariable String department){
		logger.info("Request to fetch employees by Department: {}", department);
		return managementService.getElementbyDepartment(department);
	}
}
