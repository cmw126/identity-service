package com.identity.model.validation;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * @author aswani.tiwari
 *
 */
public class ValidationUtils {

	/**
	 * Validate string is empty or null or containing only space.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean checkForBlank(String value) {
		if (StringUtils.isBlank(value)) {
			return true;
		}
		return false;
	}

	/**
	 * Its check the max and min length both condition
	 * 
	 * @param value
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean checkForMaxMinLength(String value, int min, int max) {
		if (checkForBlank(value)) {
			return false;
		}
		int valueLength = value.length();
		if ((min <= valueLength) && (valueLength <= max)) {
			return true;
		}
		return false;
	}

	/**
	 * Validate the exact length of string
	 * 
	 * @param value
	 * @param length
	 * @return
	 */
	public static boolean checkForExactLength(String value, int length) {
		if (checkForBlank(value)) {
			return false;
		}
		if (value.length() == length) {
			return true;
		}
		return false;
	}

	/**
	 * Validate the allowed values in the system for the properties
	 * 
	 * @param value
	 * @param allowedValues
	 * @return
	 */
	public static boolean checkForAllowedValues(String value, List<String> allowedValues) {
		if (checkForBlank(value)) {
			return false;
		}
		for (String allowedValue : allowedValues) {
			if (allowedValue.equals(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Validate total repair cost
	 * 
	 * @param totalPartCost
	 * @param unitAfterDecimal
	 * @return
	 */
	public static boolean checkForDoubleValue(BigDecimal totalPartCost, int unitAfterDecimal) {
		String asString = totalPartCost.toString();
		int decimalPlaces = 0;
		// Decimal not found
		if (asString.indexOf(".") == -1) {
			asString = asString + ".00";
		} else {
			// Decimal found but only one value after decimal
			decimalPlaces = asString.length() - asString.indexOf(".") - 1;
			if (decimalPlaces < 2) {
				asString = asString + "0";
			}
		}
		if (decimalPlaces <= unitAfterDecimal) {
			return true;
		}
		return false;
	}

	public static boolean checkForMaxMinLengthDobule(BigDecimal totalPartCost, int min, int max) {
		// Replace decimal with empty string- because it count as string
		String asString = totalPartCost.toString().replace(".", "");
		return checkForMaxMinLength(asString, min, max);
	}

	public static boolean checkNumeric(String caseDate) {
		return StringUtils.isNumeric(caseDate);
	}

	public static boolean checkValidDate(String caseDate, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(caseDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}
}
