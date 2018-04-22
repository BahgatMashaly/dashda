/**
 * 
 */
package com.dashda.service.components;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.Transactional;

import com.dashda.controllers.dto.AbstractDTO;
import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ListResponse;
import com.dashda.controllers.dto.OkResponse;


/**
 * @author mhanafy
 *
 */
@Transactional
@PropertySource("classpath:exception-messages.properties")
public abstract class ServicesManager {
	
	
	@Value("${ERROR_CODE_1001}")
	protected String ERROR_CODE_1001;
	
	@Value("${ERROR_CODE_1002}")
	protected String ERROR_CODE_1002;
	

	@Value("${ERROR_CODE_1003}")
	protected String ERROR_CODE_1003;
	
	@Value("${ERROR_CODE_1004}")
	protected String ERROR_CODE_1004;
	
	@Value("${ERROR_CODE_1005}")
	protected String ERROR_CODE_1005;
	
	@Value("${ERROR_CODE_1006}")
	protected String ERROR_CODE_1006;
	
	@Value("${ERROR_CODE_1007}")
	protected String ERROR_CODE_1007;
	
	@Value("${ERROR_CODE_1008}")
	protected String ERROR_CODE_1008;
	
	@Value("${ERROR_CODE_1009}")
	protected String ERROR_CODE_1009;
	
	@Value("${ERROR_CODE_1010}")
	protected String ERROR_CODE_1010;
	
	@Value("${ERROR_CODE_1011}")
	protected String ERROR_CODE_1011;
	
	@Value("${ERROR_CODE_1012}")
	protected String ERROR_CODE_1012;
	
	@Value("${ERROR_CODE_1013}")
	protected String ERROR_CODE_1013;
	
	@Value("${ERROR_CODE_1014}")
	protected String ERROR_CODE_1014;
	
	@Value("${ERROR_CODE_1015}")
	protected String ERROR_CODE_1015;
	/**
	 * THIS ATTRIBUTE NOT USED
	 */
	@Autowired
	PropertySourcesPlaceholderConfigurer properties;

	protected final Logger log = LoggerFactory.getLogger(ServicesManager.class);
	
	protected ModelMapper mapper = new ModelMapper();
	

	protected AppResponse deleteResponse(String message) {
		AppResponse appResponse = new AppResponse();
		
		appResponse.setStatus(202);
		appResponse.setMessage(message);
		
		return appResponse;
	}
	
	protected AppResponse createResponse(AbstractDTO abstractDTO, String message) {
		OkResponse okResponse = new OkResponse();
		okResponse.setStatus(201);
		okResponse.setMessage(message);
		okResponse.setData(abstractDTO);
		
		return okResponse;
	}
	
	protected AppResponse okResponse(AbstractDTO abstractDTO, String message) {
		
		OkResponse okResponse = new OkResponse();
		okResponse.setStatus(200);
		okResponse.setMessage(message);
		okResponse.setData(abstractDTO);
		
		return okResponse;
	}
	
	protected AppResponse okListResponse(List<AbstractDTO> abstractDTOs, String message) {
		
		ListResponse postResponse = new ListResponse();
		postResponse.setStatus(200);
		postResponse.setMessage(message);
		postResponse.setData(abstractDTOs);
		
		return postResponse;
		
	}
	
	private AppResponse handleResponse(int statusCode, List<AbstractDTO> data, String message) {
		ListResponse postResponse = new ListResponse();
		postResponse.setStatus(statusCode);
		postResponse.setMessage(message);
		postResponse.setData(data);
		
		return postResponse;
	}
}
