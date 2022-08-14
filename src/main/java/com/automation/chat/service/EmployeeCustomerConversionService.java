package com.automation.chat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.automation.chat.constant.Util;
import com.automation.chat.dto.ConversionResponse;
import com.automation.chat.dto.CustomerResponse;
import com.automation.chat.dto.EmployeeResponse;
import com.automation.chat.dto.MessageHistory;
import com.automation.chat.dto.StartEmployeeConversionResponse;
import com.automation.chat.model.ConversionHistory;
import com.automation.chat.model.EmployeeCustomerConversion;
import com.automation.chat.repository.CustomerRepository;
import com.automation.chat.repository.EmployeeCustomerConversionRepository;
import com.automation.chat.repository.EmployeeRepository;

@Service
public class EmployeeCustomerConversionService {

	@Autowired
	private EmployeeCustomerConversionRepository employeeCustomerConversionRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Transactional
	public ConversionResponse getChatHistory(Long employeeId, Long customerId) {		
		EmployeeCustomerConversion employeeCustomerConversion = employeeCustomerConversionRepository.fetchEmployeeCustomerConversion(employeeId, customerId);
		
		if(employeeCustomerConversion == null) 
			return new ConversionResponse();
		
		List<MessageHistory> messageHistory = new ArrayList<MessageHistory>();
		for(ConversionHistory conversionHistory : employeeCustomerConversion.getConversionHistoryList()) {
			messageHistory.add(new MessageHistory(conversionHistory.getMessageType(), conversionHistory.getMessage(), Util.formatter.format(conversionHistory.getCreatedAt().toLocalDateTime())));
		}
		
		ConversionResponse conversionResponse = new ConversionResponse();
		conversionResponse.setEmployee(new EmployeeResponse(employeeCustomerConversion.getEmployee().getId(), employeeCustomerConversion.getEmployee().getName(), employeeCustomerConversion.getEmployee().getNumber()));
		conversionResponse.setCustomer(new CustomerResponse(employeeCustomerConversion.getCustomer().getId(), employeeCustomerConversion.getCustomer().getName(), employeeCustomerConversion.getCustomer().getNumber()));
		conversionResponse.setConversionStartTIme(Util.formatter.format(employeeCustomerConversion.getCreatedAt().toLocalDateTime()));
		conversionResponse.setMessageHistory(messageHistory);
		return conversionResponse;
	}

	public StartEmployeeConversionResponse startEmployeeConversionRequest(Long employeeId, Long customerId) {
		EmployeeCustomerConversion employeeCustomerConversion = new EmployeeCustomerConversion();
		employeeCustomerConversion.setEmployee(employeeRepository.getReferenceById(employeeId));
		employeeCustomerConversion.setCustomer(customerRepository.getReferenceById(customerId));
		employeeCustomerConversionRepository.save(employeeCustomerConversion);
		
		return new StartEmployeeConversionResponse("Employee conversion has been started succesfully");
	}
}
