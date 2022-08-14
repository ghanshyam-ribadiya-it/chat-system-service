package com.automation.chat.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversionResponse {
	private CustomerResponse customer;
	private EmployeeResponse employee;
	private String conversionStartTIme;
	private List<MessageHistory> messageHistory;
}