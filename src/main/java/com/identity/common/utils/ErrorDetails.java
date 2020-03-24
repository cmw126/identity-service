package com.identity.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.identity.model.validation.ErrorTypeWithCode;

import lombok.Data;


@Data
public class ErrorDetails {
	private String code;
	private String error;
	private List<String> message;
	@JsonIgnore
	private Object[] replaceValue;
	@JsonIgnore
	private int specificCode;

	public ErrorDetails(ErrorTypeWithCode errorTypeWithCode, List<String> message,int specificCode) {
		this.code = errorTypeWithCode.getCode().getCodeValue();
		this.error = errorTypeWithCode.getType().getTypeValue();
		this.message = message;
		this.specificCode=specificCode;
	}

	public ErrorDetails(ErrorTypeWithCode errorTypeWithCode, String message,Object[] replaceValue,int specificCode) {
		this.message = new ArrayList<>();
		this.message.add(message);
		this.code = errorTypeWithCode.getCode().getCodeValue();
		this.error = errorTypeWithCode.getType().getTypeValue();
		this.replaceValue = replaceValue;
		this.specificCode = specificCode;
	}
	
	public ErrorDetails(ErrorTypeWithCode errorTypeWithCode, Object [] replaceValue,int specificCode) {
		this.message = new ArrayList<>();
		this.code = errorTypeWithCode.getCode().getCodeValue();
		this.error = errorTypeWithCode.getType().getTypeValue();
		this.replaceValue = replaceValue;
	}

	public ErrorDetails(ErrorTypeWithCode errorTypeWithCode, String message,int specificCode) {
		this.message = new ArrayList<>();
		this.message.add(message);
		this.code = errorTypeWithCode.getCode().getCodeValue();
		this.error = errorTypeWithCode.getType().getTypeValue();
	}
	
	public ErrorDetails(ErrorTypeWithCode errorTypeWithCode,int specificCode) {
		this.message = new ArrayList<>();
		this.code = errorTypeWithCode.getCode().getCodeValue();
		this.error = errorTypeWithCode.getType().getTypeValue();
		this.specificCode=specificCode;
	}

	public ErrorDetails() {
		
	}
}
