package com.identity.common.utils;

import static com.ams.claimapi.claim.constants.ClaimConstants.RESPONSE_SUCCESS;

import org.springframework.http.ResponseEntity;



public class ResponseBuilder {

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
}
