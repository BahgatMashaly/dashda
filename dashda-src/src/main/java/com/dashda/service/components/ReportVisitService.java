/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.dashda.annotation.LogExecutionTime;
import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.report.ReportTargetVisitInputDTO;
import com.dashda.controllers.dto.report.ReportUnVisitInputDTO;
import com.dashda.controllers.dto.visit.VisitDetailInputDTO;
import com.dashda.controllers.dto.visit.VisitsListInputDTO;
import com.dashda.controllers.dto.visit.VisitsPerEmployeeInputDTO;
import com.dashda.exception.ReportVisitServiceException;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportVisitService {

	AppResponse getTargetVisits(String username, ReportTargetVisitInputDTO reportTargetVisitInputDTO) throws ReportVisitServiceException;

	AppResponse getUnVisits(String username, ReportUnVisitInputDTO reportUntVisitInputDTO) throws ReportVisitServiceException;

	AppResponse getVisitsList(String username, VisitsListInputDTO visitDetailsInputDTO) throws ReportVisitServiceException, ParseException;

	AppResponse getVisitDetail(String username, @Valid VisitDetailInputDTO visitDetailInputDTO) throws ReportVisitServiceException, ParseException;
	
	AppResponse visitsPerEmployee(String username, @Valid VisitsPerEmployeeInputDTO visitsPerEmployeeInputDTO) throws ReportVisitServiceException, ParseException;

}
