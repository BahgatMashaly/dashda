/**
 * 
 */
package com.dashda.data.repositories.employee;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeHierarchy;
import com.dashda.data.repositories.AbstractDao;

/**
 * @author mhanafy
 *
 */
@Repository
public class EmployeeHierarchyDaoImpl extends AbstractDao implements EmployeeHierarchyDao {

	public EmployeeHierarchyDaoImpl() {
		super();
		this.setDAOClass(EmployeeHierarchy.class);
	}

	
	@Override
	public List<EmployeeHierarchy> getSubordinates(Employee manager, int higherLevelApproval) {
		Criteria criteria = getSession().createCriteria(EmployeeHierarchy.class);
		criteria.add(Restrictions.eq("manager", manager));
		criteria.add(Restrictions.le("structureLevel", higherLevelApproval));
		
		return criteria.list();
	}


	@Override
	public List<EmployeeHierarchy> getManagers(Employee employee) {
		Criteria criteria = getSession().createCriteria(EmployeeHierarchy.class);
		criteria.add(Restrictions.eq("employee", employee));
		criteria.addOrder(Order.desc("structureLevel"));
		return criteria.list();
	}


	@Override
	public EmployeeHierarchy employeeHierarchy(Employee employee, Employee manager) {
		Criteria criteria = getSession().createCriteria(EmployeeHierarchy.class);
		criteria.add(Restrictions.eq("employee", employee));
		criteria.add(Restrictions.eq("manager", manager));
		
		return (EmployeeHierarchy)criteria.uniqueResult();
	}

}
