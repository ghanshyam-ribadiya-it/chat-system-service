package com.automation.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automation.chat.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
