package com.identity.model.validation;

import lombok.Value;

@Value
public class ErrorTypeWithCode {

	private ErrorType type;
	private ErrorCode code;

}
