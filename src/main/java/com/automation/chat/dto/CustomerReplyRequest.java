package com.automation.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerReplyRequest {
	private Long employeeId;
	private Long customerId;
	private String message;
}
