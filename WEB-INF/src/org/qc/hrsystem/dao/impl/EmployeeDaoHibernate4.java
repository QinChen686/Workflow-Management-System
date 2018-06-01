package org.qc.hrsystem.dao.impl;
import java.util.*;
import org.qc.hrsystem.dao.EmployeeDao;
import org.qc.hrsystem.domain.Employee;
import org.qc.common.Dao.impl.BaseDaoHibernate4;

public class EmployeeDaoHibernate4 extends BaseDaoHibernate4<Employee> implements EmployeeDao
{
	public List<Employee> findByNameAndPass(Employee emp)
	{
		return find("selcet a from employee as a where a.pass=?0 and a.name=?1",emp.getPass(),emp.getName());
	}
	
	public Employee findByName(String name)
	{
		List<Employee> emps=find("selcet a from Employee as a where a.name=?0",name);
		if(emps!=null&&emps.size()>=1)
		{
			return emps.get(0);
		}
		return null;
	}
}