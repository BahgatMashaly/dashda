/**
 * 
 */
package com.dashda.service.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.AbstractDTO;
import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.DoctorDTO;
import com.dashda.controllers.dto.ListResponse;
import com.dashda.data.entities.Contact;
import com.dashda.data.entities.District;
import com.dashda.data.entities.Doctor;
import com.dashda.data.entities.EmployeeDoctor;
import com.dashda.data.entities.EmployeesCoveredDistrict;
import com.dashda.data.entities.User;
import com.dashda.data.repositories.DoctorDao;
import com.dashda.data.repositories.UserDao;
import com.dashda.exception.DoctorServiceExceptionManager;

import ch.qos.logback.core.util.COWArrayList;

/**
 * @author mhanafy
 *
 */
@Service("DoctorsListService")
public class DoctorsListServiceImpl extends ServicesManager implements DoctorsListService {

	/* (non-Javadoc)
	 * @see com.dashda.service.components.DoctorsListService#serviceList()
	 */
	
	@Autowired
	DoctorDao doctorDao;
	
	@Autowired
	UserDao userDao;

	private List<District> districts;
	
	private Doctor doctor;
	
	private DoctorDTO doctorDTO;
	
	private List<AbstractDTO> doctorDTOs;
	
	private Contact contact;

	
	@Override
	public AppResponse doctorsList(String username) throws DoctorServiceExceptionManager {
		
		User user = userDao.findUserByUsername(username);
		districts = new ArrayList<District>();

		
		if(user.getEmployee() == null)
			throw new DoctorServiceExceptionManager(ERROR_CODE_1001);
		if(user.getEmployee().getEmployeesCoveredDistricts().size() == 0)
			throw new DoctorServiceExceptionManager(ERROR_CODE_1002);
		
		for(Iterator<EmployeesCoveredDistrict> employeesCoveredDistrictIt = 
				user.getEmployee().getEmployeesCoveredDistricts().iterator(); 
				employeesCoveredDistrictIt.hasNext();) {
			districts.add(((EmployeesCoveredDistrict)employeesCoveredDistrictIt.next()).getDistrict());
		}
		
		List<Doctor> doctors = doctorDao.doctorsList(districts);
		
		if(doctors.isEmpty())
			throw new DoctorServiceExceptionManager(ERROR_CODE_1010);
		
		doctorDTOs = new ArrayList<AbstractDTO>();
		

		
		for(Iterator<Doctor> doctorsIt = doctors.iterator(); doctorsIt.hasNext();) {
			doctor = (Doctor)doctorsIt.next();
			contact = doctor.getContact();
			
			doctorDTO = new DoctorDTO();
			
			mapper.map(doctor.getContact(), doctorDTO);
			mapper.map(doctor, doctorDTO);
			
			
			
			for (Iterator employeeDoctorIt = doctor.getEmployeesDoctors().iterator(); employeeDoctorIt.hasNext();) {
				EmployeeDoctor employeeDoctor = (EmployeeDoctor) employeeDoctorIt.next();
				if(employeeDoctor.getEmployee().getId() == user.getEmployee().getId())
					doctorDTO.setAssignedId(employeeDoctor.getId());
			}

			
			
			doctorDTOs.add(doctorDTO);
		}

		
		return okListResponse(doctorDTOs, "GET Service :: Doctors List");
	}

}
