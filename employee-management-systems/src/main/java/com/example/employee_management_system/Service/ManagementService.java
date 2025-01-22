package com.example.employee_management_system.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.employee_management_system.Pojo.EmployeePojo;

import jakarta.annotation.PostConstruct;

@Service
public class ManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementService.class);
	List<EmployeePojo> data = new ArrayList<>();
	
	 @Value("${profiling}")
	 private String message;
	 
	 @PostConstruct
	 public void init() {
		 logger.info("Message : " + message);
	 }
	
//	Adding the Employee
	
	public String AddEmployee(@RequestBody EmployeePojo employeePojo) {
		 boolean added = data.add(employeePojo);
		 
		if(added) {
			 logger.info("Employee added: {}", employeePojo);  // Log employee details when added
			return "Employee is Added!";
		}else {
			logger.error("Failed to add employee: {}", employeePojo);  // Log an error if adding fails
			return "Employee is Not Added!";
		}
	}
	
	 // Retrieving All Employees
	
	public List<EmployeePojo> getEmployees() {
		logger.info("Fetching all employees");
	    return data;
	}
	
//	Get Employee by ID
	
	public EmployeePojo getEmployeeByID(int id) {
		
		for(EmployeePojo employee : data) {
			if(employee.getId() == id) {
				logger.info("Employee found by ID: {}", employee);
				return employee;
			}
		}
		logger.warn("Employee not found by ID: {}", id);
		return null;
	}

//	update Employee
	
	public ResponseEntity<String> updateEmployee(@RequestBody EmployeePojo employeePojo ,@PathVariable Integer id) {
		
		for(EmployeePojo employee : data) {
			if(employee.getId() == id) {
				employee.setName(employeePojo.getName());
				employee.setDepartment(employeePojo.getDepartment());
				employee.setSalary(employeePojo.getSalary());
				 logger.info("Employee updated: {}", employee);
				return ResponseEntity.ok("Employee details are updated");
			}
		}
		logger.warn("Employee not found for update: ID {}", id);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Found!");
	}
	
	
//	Delete Employee
	
	public String deleteEmployee(int id) {
		for(EmployeePojo employee : data) {
			if(employee.getId() == id) {
				data.remove(employee);
				logger.info("Employee deleted: {}", employee);
				return "Employee is deleted";
			}
		}
		logger.warn("Employee not found for deletion: ID {}", id);
		return "Employee is not found";
	}
	
//	Get Employee by name
	
	public EmployeePojo getElementByName(String name) {
		for(EmployeePojo employee : data) {
			if(employee.getName().equalsIgnoreCase(name)) {
				logger.info("Employee found by Name: {}", employee);
				return employee;
			}
		}
		logger.warn("Employee not found by Name: {}", name);
		return null;
	}
	
	
//	Get Employee by department 
	
	public List<EmployeePojo> getElementbyDepartment(String department) {
		
		List<EmployeePojo> matchingEmployees = new ArrayList<EmployeePojo>();
		
		for(EmployeePojo employeePojo : data) {
			if(employeePojo.getDepartment().equals(department)) {
				matchingEmployees.add(employeePojo);
			}
		}
		if (matchingEmployees.isEmpty()) {
            logger.warn("No employees found in department: {}", department);
        } else {
            logger.info("Employees found in department: {}", department);
        }
		return matchingEmployees.isEmpty() ? null : matchingEmployees;
	}
	
}
