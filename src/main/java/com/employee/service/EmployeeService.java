package com.employee.service;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee updateEmployee(Long id, Employee updatedEmployee) {
		return employeeRepository.findById(id).map(employee -> {
			employee.setName(updatedEmployee.getName());
			employee.setDepartment(updatedEmployee.getDepartment());
			employee.setSalary(updatedEmployee.getSalary());
			return employeeRepository.save(employee);
		}).orElseThrow(() -> new RuntimeException("Employee not found"));
	}

	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}
}