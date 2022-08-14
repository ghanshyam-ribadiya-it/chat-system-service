package com.automation.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StartEmployeeConversionRequest {
	private Long employeeId;
	private Long customerId;
}
