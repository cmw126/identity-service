package com.identity.common.utils;

import static com.identity.common.constants.UserConstants.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ResponseBuilder {
	
	@Autowired
	MessageSource messageSource;

	public ResponseEntity<ResponseObject> buildAPISuccessResponse(Object content) {

		ResponseObject resObj = new ResponseObject();
		resObj.setTimestamp(getTimestamp());
		resObj.setStatus(RESPONSE_SUCCESS);
		resObj.setContent(content);
		String transactionId =	ThreadLocalWrapper.getTransactionId();
		resObj.setTransactionId(transactionId);

		ResponseEntity<ResponseObject> res = new ResponseEntity<ResponseObject>(resObj, HttpStatus.OK);
		return res;
	}
	
	public ResponseEntity<ResponseObject> buildAPISuccessResponse(Object content,String messageCode) {

		ResponseObject resObj = new ResponseObject();
		resObj.setTimestamp(getTimestamp());
		resObj.setStatus(RESPONSE_SUCCESS);
		resObj.setContent(content);
		String msg = getMessageFromResponseCode(messageCode,null);
		resObj.setMessage(msg);
		String transactionId =	ThreadLocalWrapper.getTransactionId();
		resObj.setTransactionId(transactionId);

		ResponseEntity<ResponseObject> res = new ResponseEntity<ResponseObject>(resObj, HttpStatus.OK);
		return res;
	}

	public ResponseEntity<ResponseObject> buildAPIErrorResponse(List<ErrorDetails> errorDetailsList,
			HttpStatus httpStatus,String transactionId) {

		ErrorDetails errorDetails = new ErrorDetails();
		String code = "";
		String errorType = "";
		List<String> message = new ArrayList<>();
		for (ErrorDetails error : errorDetailsList) {
			code = error.getCode();
			errorType = error.getError();
			String specifcMessage = getMessageFromResponseCode(error.getSpecificCode()+"",null);
			String msg = getMessageFromResponseCode(code, new Object[]{specifcMessage});
			message.add(msg);
		}
		errorDetails.setCode(code);
		errorDetails.setError(errorType);
		errorDetails.setMessage(message);

		ResponseObject resObj = new ResponseObject();
		resObj.setTimestamp(getTimestamp());
		resObj.setStatus(RESPONSE_FAILED);
		resObj.setTransactionId(transactionId);
		resObj.setContent(errorDetails);

		ResponseEntity<ResponseObject> res = new ResponseEntity<ResponseObject>(resObj, httpStatus);
		log.info("TransactionId {} :-  Controller Return value {}: " , transactionId,res);
		log.info("TransactionId {} :-  End Response of Controller" , transactionId);

		return res;
	}

	private String getMessageFromResponseCode(String messageCode, Object[] replaceValue) {
		String message = messageSource.getMessage(messageCode, replaceValue, new Locale("en_US"));
		return message;
	}
	
	private String getTimestamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss z");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String timestamp = sdf.format(new Date());
		return timestamp;
	}
}
