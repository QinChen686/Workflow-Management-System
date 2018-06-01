package org.qc.hrsystem.dao;
import java.util.*;
import org.qc.hrsystem.domain.*;
import org.qc.common.Dao.BaseDao;

public interface ManagerDao extends BaseDao<Manager>
{
	List<Manager> findByNameAndPass(Manager mgr);
	
	Manager findByName(String name);
	
	//Manager findByEmp(Employee emp);
}