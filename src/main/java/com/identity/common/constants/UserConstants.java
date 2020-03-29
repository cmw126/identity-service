package com.identity.common.constants;

import com.identity.model.validation.ErrorCode;
import com.identity.model.validation.ErrorType;
import com.identity.model.validation.ErrorTypeWithCode;

public interface UserConstants {

	final String RESPONSE_SUCCESS = "OK";
	final String RESPONSE_FAILED = "Failed";
	final String RESPONSE_INSERTED = "1001";
	final String RESPONSE_UPDATED = "1002";
	final String RESPONSE_DELETED = "1003";
	final String RESPONSE_DUPLICATE = "1004";
	
	// user status
	final String ACTIVE = "Active";
	final String INACTIVE = "Inactive";	
	
	// Generic API Error Type
	ErrorTypeWithCode API_ERROR_INVALID_REQUEST_BODY = new ErrorTypeWithCode(new ErrorType("Invalid Request Body"), new ErrorCode("200.002"));
	ErrorTypeWithCode API_ERROR_UNAUTHORIZED = new ErrorTypeWithCode(new ErrorType("Unauthorized"),new ErrorCode("200.001"));
	ErrorTypeWithCode API_ERROR_UPDATE_ERROR = new ErrorTypeWithCode(new ErrorType("Update Error"), new ErrorCode("200.003"));
	ErrorTypeWithCode API_ERROR_INVALID_REQUEST_PARAMETER = new ErrorTypeWithCode(new ErrorType("Invalid Request Parameter"),new ErrorCode("200.004"));
	ErrorTypeWithCode API_ERROR_INTERNAL_SERVER_ERROR = new ErrorTypeWithCode(new ErrorType("Internal Server Error"),new ErrorCode("200.100"));
	ErrorTypeWithCode API_ERROR_RECORD_NOT_FOUND = new ErrorTypeWithCode(new ErrorType("Fetch Error"),new ErrorCode("200.005"));
	ErrorTypeWithCode API_ERROR_VALUE_NOT_SUPPORTED = new ErrorTypeWithCode(new ErrorType("Value Not Allowed"),new ErrorCode("200.006"));
	ErrorTypeWithCode API_ERROR_VALUE_REJECTED_ERROR = new ErrorTypeWithCode(new ErrorType("Rejected Error"),new ErrorCode("200.007"));
	ErrorTypeWithCode API_ERROR_THIRD_PARTY_ERROR = new ErrorTypeWithCode(new ErrorType("Third Party"),new ErrorCode("200.008"));
	ErrorTypeWithCode API_ERROR_ALREADY_EXITS_ERROR = new ErrorTypeWithCode(new ErrorType("Already Exits"),new ErrorCode("200.009"));
}
