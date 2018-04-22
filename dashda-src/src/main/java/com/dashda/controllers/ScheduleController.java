/**
 * 
 */
package com.dashda.controllers;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.controllers.dto.ScheduleDTO;
import com.dashda.exception.ScheduleExceptionManager;
import com.dashda.service.components.ScheduleService;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author mhanafy
 *
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController extends AbstractController{

	
	@Autowired
	ScheduleService scheduleService;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/add-schedule-item")
	@Secured("ROLE_SCHEDULE_CREATOR")
	public ResponseEntity<ScheduleDTO> addScheduleItem(@AuthenticationPrincipal User user, @Valid @RequestBody ScheduleDTO scheduleDTOs) throws ParseException, ScheduleExceptionManager {
			return returnResponseEntityCreated(scheduleService.addScheduleItem(user.getUsername(), scheduleDTOs));
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/modify-schedule-item-data")
	@Secured("ROLE_SCHEDULE_CREATOR")
	public ResponseEntity<ScheduleDTO> modifyScheduleItem(@RequestParam(required = true) int scheduleId,
			@RequestParam(required = true) String scheduleDate) throws ParseException, ScheduleExceptionManager {
		
			return returnResponseEntityOk(scheduleService.modifyScheduleItemData(scheduleId, scheduleDate));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/approve-schedule-item")
	@Secured("ROLE_SCHEDULE_ADMIN")
	public void approveScheduleItem(@AuthenticationPrincipal User user, @RequestBody List<Integer> scheduleItems) throws ScheduleExceptionManager {
				scheduleService.approveScheduleItems(user.getUsername(), scheduleItems);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/reject-schedule-item")
	@Secured("ROLE_SCHEDULE_ADMIN")
	public void rejectScheduleItem(@AuthenticationPrincipal User user, @RequestBody List<Integer> scheduleItems) throws ScheduleExceptionManager {
		scheduleService.rejectScheduleItems(user.getUsername(), scheduleItems);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/approve-schedule")
	@Secured("ROLE_SCHEDULE_ADMIN")
	public void approveSchedule(@AuthenticationPrincipal User user, @RequestBody int employeeId) {
			scheduleService.approveSchedule(user.getUsername(), employeeId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/reject-schedule")
	@Secured("ROLE_SCHEDULE_ADMIN")
	public void rejectSchedule(@AuthenticationPrincipal User user, @RequestBody int employeeId) {
			scheduleService.rejectSchedule(user.getUsername(), employeeId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/schedule-items-list-need-attention")
	@Secured("ROLE_SCHEDULE_ADMIN")
	public String scheduleItemsList(@AuthenticationPrincipal User user) throws JsonProcessingException, ScheduleExceptionManager {
			List<ScheduleDTO> scheduleDTOs = scheduleService.scheduleItemsListNeedAttention(user.getUsername());
			return jsonObjectmapper.writeValueAsString(scheduleDTOs);
	}
}
