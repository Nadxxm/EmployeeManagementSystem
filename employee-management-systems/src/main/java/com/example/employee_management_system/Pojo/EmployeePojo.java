package com.example.employee_management_system.Pojo;

public class EmployeePojo {
	
	private int id;
	private String name;
	private String department;
	private double salary;
	@Override
	public String toString() {
		return "EmployeePojo [id=" + id + ", name=" + name + ", department=" + department + ", salary=" + salary + "]";
	}
	public EmployeePojo(int id, String name, String department, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.salary = salary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
}