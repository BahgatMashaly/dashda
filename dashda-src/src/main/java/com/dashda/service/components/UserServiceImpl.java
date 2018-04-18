/**
 * 
 */
package com.dashda.service.components;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.EmployeeUserDTO;
import com.dashda.controllers.dto.UserDTO;
import com.dashda.controllers.dto.UserPermessionDTO;
import com.dashda.data.entities.Account;
import com.dashda.data.entities.Contact;
import com.dashda.data.entities.District;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Governorate;
import com.dashda.data.entities.User;
import com.dashda.data.entities.UserRoleClientTemplate;
import com.dashda.data.entities.UserRolePermission;
import com.dashda.data.repositories.ContactDao;
import com.dashda.data.repositories.EmployeeDao;
import com.dashda.data.repositories.UserDao;

/**
 * @author mhanafy
 *
 */
@Service("userService")
public class UserServiceImpl extends ServicesManager implements UserService {

	/* (non-Javadoc)
	 * @see com.dashda.service.components.UserService#findListOfUsers()
	 */

	@Autowired
	private UserDao userDao;

	@Autowired
	private ContactDao contactDao;
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	private PasswordEncoder generatePasswordEncoder;
	
	private UserDTO userDTO;
	private User user;
	private Contact contact;
	private Account account;
	private District district;

	private Governorate governorate;
	
	@Override
	public UserDTO getUserInfo(String username) throws UserServiceExceptioManager {
		
		user = userDao.findUserByUsername(username);
				
		userDTO = prepareUserDTOObject(user);
		
		return userDTO;
	}

	@Override
	public UserDTO authorizationInfo(String username, String password) throws UserServiceExceptioManager {
		user = userDao.findActiveUserByUsernameAndPassword(username, generatePasswordEncoder.encode(password));
		
		userDTO = prepareUserDTOObject(user);
		
		return userDTO;
	}

	@Override
	public void createUser(UserDTO userDTO) throws UserServiceExceptioManager {
		createUserEntity(userDTO);
	
	}


	@Override
	public void createEmployeeUser(@Valid EmployeeUserDTO employeeUserDTO) throws UserServiceExceptioManager {
		createUserEntity(employeeUserDTO);
		
	}

	private void createUserEntity(UserDTO userDTO) throws UserServiceExceptioManager {
		
		if(userDao.findUserByUsername(userDTO.getUsername()) != null)
			throw new UserServiceExceptioManager(ERROR_CODE_1008);
		
		contact = new Contact();
		user = new User();
		account = new Account(Integer.parseInt(userDTO.getAccountId()));
		district = new District(Integer.parseInt(userDTO.getDistrictId()));
		governorate = new Governorate(Integer.parseInt(userDTO.getGovernorateId()));
		
		Employee employee = null;
		
		//initiate contact object
		contact.setDistrict(district);
		contact.setGovernorate(governorate);
		
		mapper.map(userDTO, contact);
		
		//used by create EmployeeUser Object
		if( userDTO instanceof EmployeeUserDTO) {
			
			EmployeeUserDTO employeeUserDTO = (EmployeeUserDTO) userDTO;
			employee = new Employee();
			
			Employee manager = null;
			
			//if this employee have manager relation
			if(employeeUserDTO.getManagerId() != null) {
				
				int managerId = Integer.parseInt(employeeUserDTO.getManagerId());
				manager = employeeDao.findEmployeeByID(managerId);
				
				if(manager == null) {
					throw new UserServiceExceptioManager(ERROR_CODE_1009);
				}
			}
			
			mapper.map(userDTO, employee);
			employee.setContact(contact);
			employee.setManager(manager);
		}
		
		
		mapper.map(userDTO, user);
		
		user.setPassword(generatePasswordEncoder.encode(userDTO.getPassword()));
		user.setAccount(account);
		
		// persist on DB
		if(employee != null)
			employeeDao.createEmployee(employee);
		contactDao.createContact(contact);
		
		if(employee != null)
			user.setEmployee(employee);
		user.setContact(contact);
		
		userDao.createUser(user);
		
	}

private UserDTO prepareUserDTOObject(User user) throws UserServiceExceptioManager {
		
	if(user == null)
		throw new UserServiceExceptioManager(ERROR_CODE_1014);
		
		List<String> userPermessions = new ArrayList<String>();
		List<String> userRoleClientTemplates = new ArrayList<String>();
		
		
		if(user.getEmployee() != null) {
			userDTO = new EmployeeUserDTO();
			mapper.map(user.getEmployee(), userDTO);
			if(user.getEmployee().getManager() != null)
				((EmployeeUserDTO)userDTO).setManagerId(user.getEmployee().getManager().getId()+"");
			
		}else {
			userDTO = new UserDTO();
		}
			
		
		mapper.map(user.getContact(), userDTO);
		mapper.map(user, userDTO);
		
		for(Iterator<UserRolePermission> userRolePermissionIt = user.getUserRole().getUserRolePermissions().iterator(); userRolePermissionIt.hasNext();) {
			userPermessions.add((userRolePermissionIt.next()).getPermission().getName());
		}
		
		
		for (Iterator<UserRoleClientTemplate> userRoleClienttemplateIt = user.getUserRole().getUserRoleClientTemplate().iterator(); userRoleClienttemplateIt.hasNext();) {
			userRoleClientTemplates.add((userRoleClienttemplateIt.next()).getClientTemplate().getTemplate());
		}
		
		userDTO.setUserPermessions(userPermessions);
		userDTO.setClientTemplates(userRoleClientTemplates);
		userDTO.setPassword("***********");
		
		return userDTO;
		
	}
	
	
}
