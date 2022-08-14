package com.automation.chat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automation.chat.dto.EmployeeResponse;
import com.automation.chat.model.Employee;
import com.automation.chat.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<EmployeeResponse> findAllEmployee() {
		List<EmployeeResponse> employeeResponseList = new ArrayList<>();

		List<Employee> employeeList = employeeRepository.findAll();
		EmployeeResponse employeeResponse = null;
		for (Employee employee : employeeList) {
			employeeResponse = new EmployeeResponse(employee.getId(), employee.getName(), employee.getNumber());
			employeeResponseList.add(employeeResponse);
		}

		return employeeResponseList;
	}
}
