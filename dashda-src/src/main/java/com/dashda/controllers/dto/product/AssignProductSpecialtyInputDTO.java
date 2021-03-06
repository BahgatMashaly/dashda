/**
 * 
 */
package com.dashda.controllers.dto.product;

import java.util.List;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mohamed.hanfy
 *
 */
public class AssignProductSpecialtyInputDTO implements AbstractDTO {

	private int specialtyId;
	private List<Integer> productIds;
	
	
	public int getSpecialtyId() {
		return specialtyId;
	}
	public void setSpecialtyId(int specialtyId) {
		this.specialtyId = specialtyId;
	}
	public List<Integer> getProductIds() {
		return productIds;
	}
	public void setProductIds(List<Integer> productIds) {
		this.productIds = productIds;
	}
	
	
}
