/**
 * 
 */
package com.dashda.controllers.dto;

import javax.validation.constraints.NotEmpty;

/**
 * @author mohamed.hanfy
 *
 */
public class VisitsPerEmployeeInputDTO implements AbstractDTO {

	@NotEmpty
	private String dateFrom;
	@NotEmpty
	private String dateTo;
	
	
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	
	
}