package com.automation.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.automation.chat.dto.CustomerReplyRequest;
import com.automation.chat.dto.CustomerReplyResponse;
import com.automation.chat.model.ConversionHistory;
import com.automation.chat.model.EmployeeCustomerConversion;
import com.automation.chat.repository.ConversionHistoryRepository;
import com.automation.chat.repository.EmployeeCustomerConversionRepository;

@Service
public class ConversionHistoryService {
	
	@Autowired
	private ConversionHistoryRepository conversionHistoryRepository;
	
	@Autowired
	private EmployeeCustomerConversionRepository employeeCustomerConversionRepository;
	
	@Transactional
	public CustomerReplyResponse saveCustomerReply(CustomerReplyRequest customerReplyRequest) {
		EmployeeCustomerConversion employeeCustomerConversion = employeeCustomerConversionRepository.fetchLatestEmployeeCustomerConversion(customerReplyRequest.getEmployeeId(), customerReplyRequest.getCustomerId(), false);
		employeeCustomerConversion.setReplyByCustomer(true);
		employeeCustomerConversionRepository.save(employeeCustomerConversion);
		
		ConversionHistory conversionHistory = new ConversionHistory();
		conversionHistory.setEmployeeCustomerConversion(employeeCustomerConversion);
		conversionHistory.setMessageType("Incoming");
		conversionHistory.setMessage(customerReplyRequest.getMessage());
		conversionHistoryRepository.save(conversionHistory);
		return new CustomerReplyResponse("Your reply to employee is saved succesfully");
	}

}
