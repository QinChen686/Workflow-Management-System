package org.qc.hrsystem.dao.impl;
import java.util.*;
import org.qc.hrsystem.dao.PaymentDao;
import org.qc.hrsystem.domain.*;
import org.qc.common.Dao.impl.BaseDaoHibernate4;

public class PaymentDaoHibernate4 extends BaseDaoHibernate4<Payment> implements PaymentDao
{
	public List<Payment> findByEmp(Employee emp)
	{
		return find("select a from payment as a where a.employee?0 ", emp);
	}
	
	public Payment findByEmpAndMonth(Employee emp, String month)
	{
		List<Payment> payments=find("selcet p from payment as p where p.employee=?0 and p.employee.month=?1",emp,month);
		if(payments!=null && payments.size()>=1)
		{
			return payments.get(0);
		}
		return null;
	}
}