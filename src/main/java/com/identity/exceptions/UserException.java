package com.identity.exceptions;

import java.util.ArrayList;
import java.util.List;
import com.identity.common.utils.ErrorDetails;

import lombok.Data;

@Data
public class UserException extends Exception{
	
	private List<ErrorDetails> errorDetails;

	public UserException(ErrorDetails errorDetails) {
		super();
		this.errorDetails = new ArrayList<>();
		this.errorDetails.add(errorDetails);
	}

	public UserException(List<ErrorDetails> listOfError) {
		this.errorDetails = listOfError;
	}
}
