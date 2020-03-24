package com.identity.model.validation;



import java.util.ArrayList;
import java.util.List;

import com.identity.common.utils.ErrorDetails;

public class FieldValidationResult {

	private List<ErrorDetails> message = new ArrayList<ErrorDetails>();

	public void addError(ErrorDetails error) {
		message.add(error);
	}

	public boolean hasError() {
		return !message.isEmpty();
	}

	public List<ErrorDetails> getErrors() {
		return message;
	}

	@Override
	public String toString() {
		return "FieldValidationResult [message=" + message + "]";
	}
}
