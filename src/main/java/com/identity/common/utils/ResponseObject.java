package com.identity.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject {
	private String timestamp;
	private String status;
	private String transactionId;
	Object content;
	private String message;

}
