package org.qc.hrsystem.dao;
import java.util.*;
import org.qc.common.Dao.BaseDao;
import org.qc.hrsystem.domain.*;

public interface EmployeeDao extends BaseDao<Employee>
{
	List<Employee> findByNameAndPass(Employee emp);
	
	Employee findByName(String name);
	
}