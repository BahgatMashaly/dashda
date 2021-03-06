/**
 * 
 */
package com.dashda.controllers.dto.visit;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

/**
 * @author mhanafy
 *
 */
public class VisitAddCommentInputDTO {

	@Digits(fraction=0, integer=8)
	private int id;
	@NotEmpty
	private String comment;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
