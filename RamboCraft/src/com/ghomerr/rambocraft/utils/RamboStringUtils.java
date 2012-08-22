package com.ghomerr.rambocraft.utils;

import com.ghomerr.rambocraft.constants.RamboConstants;

/**
 * String Utils
 * 
 * @author Ghomerr
 */
public class RamboStringUtils
{
	/**
	 * Check if a string is blank
	 * 
	 * @param str to check
	 * @return true if blank
	 */
	public static boolean isBlank(final String str)
	{
		return (str == null || str.isEmpty() || str.matches(RamboConstants.BLANK_STRING_PATTERN));
	}

	/**
	 * Check if a string is not blank
	 * 
	 * @param str to check
	 * @return true if not blank
	 */
	public static boolean isNotBlank(final String str)
	{
		return !isBlank(str);
	}
	
	/**
	 * Check if a string is composed with valid numbers (floats included)
	 * @param str
	 * @return true if str is a number
	 */
	public static boolean isInteger(final String str)
	{
		return str != null && str.matches("^-?[0-9]+$");
	}
}
