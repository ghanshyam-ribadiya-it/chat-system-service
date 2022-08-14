package com.automation.chat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.automation.chat.dto.ConversionResponse;
import com.automation.chat.dto.CustomerReplyRequest;
import com.automation.chat.dto.CustomerReplyResponse;
import com.automation.chat.dto.CustomerResponse;
import com.automation.chat.dto.EmployeeResponse;
import com.automation.chat.dto.StartEmployeeConversionRequest;
import com.automation.chat.dto.StartEmployeeConversionResponse;
import com.automation.chat.service.ConversionHistoryService;
import com.automation.chat.service.CustomerService;
import com.automation.chat.service.EmployeeCustomerConversionService;
import com.automation.chat.service.EmployeeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
public class ChatController {

	private EmployeeCustomerConversionService employeeCustomerConversionService;
	private EmployeeService employeeService;
	private CustomerService customerService;
	private ConversionHistoryService conversionHistoryService;

	@GetMapping(value = "/chatHistory/employee/{employeeId}/customer/{customerId}")
	public ConversionResponse getChatHistory(@PathVariable Long employeeId, @PathVariable Long customerId) {
		log.info("Request for employeeId : {}, customerId : {}", employeeId, customerId);
		return employeeCustomerConversionService.getChatHistory(employeeId, customerId);
	}

	@GetMapping(value = "/employees")
	public List<EmployeeResponse> getAllEmployee() {
		log.info("Get all employees");
		return employeeService.findAllEmployee();
	}

	@GetMapping(value = "/customers")
	public List<CustomerResponse> getAllCustomer() {
		log.info("Get all customers");
		return customerService.findAllCustomer();
	}
	
	@PostMapping(value = "/startConversion")
	public StartEmployeeConversionResponse startStartEmployeeConversion(@RequestBody StartEmployeeConversionRequest startEmployeeConversionRequest) {
		log.info("Request for startEmployeeConversionRequest : {}", startEmployeeConversionRequest);
		return employeeCustomerConversionService.startEmployeeConversionRequest(startEmployeeConversionRequest.getEmployeeId(), startEmployeeConversionRequest.getCustomerId());
	}
	
	@PostMapping(value = "/customer/reply")
	public CustomerReplyResponse customerReplyToMessage(@RequestBody CustomerReplyRequest customerReplyRequest) {
		log.info("Request for customerReplyToMessage : {}", customerReplyRequest);
		return conversionHistoryService.saveCustomerReply(customerReplyRequest);
	}
}