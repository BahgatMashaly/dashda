/**
 * 
 */
package com.dashda.exception;

/**
 * @author mhanafy
 *
 */
public class MyServiceProvidersListServiceExceptionManager extends AppExceptionHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public MyServiceProvidersListServiceExceptionManager() {
		super();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public MyServiceProvidersListServiceExceptionManager(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public MyServiceProvidersListServiceExceptionManager(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 */
	public MyServiceProvidersListServiceExceptionManager(String arg0) {
		super(arg0);
		
	}

	/**
	 * @param arg0
	 */
	public MyServiceProvidersListServiceExceptionManager(Throwable arg0) {
		super(arg0);
	}



}
