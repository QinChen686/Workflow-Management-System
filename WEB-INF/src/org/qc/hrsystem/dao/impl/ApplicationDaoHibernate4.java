package org.qc.hrsystem.dao.impl;
import java.util.*;
import org.qc.hrsystem.dao.*;
import org.qc.common.Dao.impl.BaseDaoHibernate4;
import org.qc.hrsystem.domain.*;

public class ApplicationDaoHibernate4 extends BaseDaoHibernate4<Application> implements ApplicationDao
{
	public List<Application> findByEmp(Employee emp)
	{
		return find("select a from Application as a where a.attend.employee=?0",emp);
	}
}