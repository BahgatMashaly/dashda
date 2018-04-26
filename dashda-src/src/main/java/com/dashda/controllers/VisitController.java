/**
 * 
 */
package com.dashda.controllers;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.controllers.dto.RequestInput;
import com.dashda.controllers.dto.VisitDTO;
import com.dashda.controllers.dto.VisitInquiryDTO;
import com.dashda.exception.VisitServiceException;
import com.dashda.service.components.VisitService;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author mhanafy
 *
 */

@RestController
@RequestMapping("/visit")
@Secured("ROLE_VISIT_CONTRIBUTOR")
public class VisitController extends AbstractController {

	@Autowired
	VisitService visitService;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/visits")
	public ResponseEntity visitItemsList(@AuthenticationPrincipal User user, @Validated @RequestBody VisitInquiryDTO visitInquiryDto) throws VisitServiceException, ParseException{
		return returnResponseEntityOk(visitService.visitItemsList(user.getUsername(), visitInquiryDto));
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/complete-visits")
	public ResponseEntity completeVisits(@AuthenticationPrincipal User user, @RequestBody RequestInput requestInput) throws VisitServiceException {
		return returnResponseEntityOk(visitService.completeVisits(user.getUsername(), requestInput));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/discard-visits")
	public ResponseEntity discardVisits(@AuthenticationPrincipal User user, @RequestBody RequestInput requestInput) throws VisitServiceException {
		return returnResponseEntityOk(visitService.dicardVisits(user.getUsername(), requestInput));
	}
}
