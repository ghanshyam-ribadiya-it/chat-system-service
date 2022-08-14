package com.automation.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageHistory {
	private String messageType;
	private String message;
	private String time;
}
