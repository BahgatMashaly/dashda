/**
 * 
 */
package com.dashda.exception;

/**
 * @author mhanafy
 *
 */
public class EmployeDistrictServiceException extends AppExceptionHandler {

	/**
	 * 
	 */
	public EmployeDistrictServiceException() {
	}

	/**
	 * @param arg0
	 */
	public EmployeDistrictServiceException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public EmployeDistrictServiceException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public EmployeDistrictServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public EmployeDistrictServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
