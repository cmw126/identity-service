package com.identity.model.validation;

import lombok.Value;

/**Using the value type. So seperate  errorCode from errorType 
 * Advantage - Since both are string , You may by mistaken exchange.It prevent from them
 * @author aswani.tiwari
 *
 */
@Value
public class ErrorType {

	private String typeValue;
}
