package org.qc.hrsystem.dao.impl;
import java.util.*;
import java.text.*;
import org.qc.hrsystem.dao.*;
import org.qc.common.Dao.impl.BaseDaoHibernate4;
import org.qc.hrsystem.domain.*;

public class AttendDaoHibernate4 extends BaseDaoHibernate4<Attend> implements AttendDao
{
	public List<Attend> findByEmpAndMonth(Employee emp, String month)
	{
		return find(" from Attend as a where a.employee=?0" + "and substring(a.dutyDay,0,7)=?1",emp,month);
	}
	
	public List<Attend> findByEmpAndDutyDay(Employee emp, String dutyDay)
	{
		return find("from Attend as a where a.employee=?0"+"and a.dutyDay=?1",emp,dutyDay);
	}
	
	public Attend findByEmpAndDutyDayAndCome(Employee emp, String dutyDay, boolean isCome)
	{
		List<Attend> l=findByEmpAndDutyDay(emp,dutyDay);
		if(l!=null&&l.size()>1)
		{
			for(Attend attend:l)
			{
				if(attend.getIsCome()==isCome)
					return attend;
			}
		}
		return null;
	}
	
	public List<Attend> findByEmpUnAttend(Employee emp, AttendType attendType)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		String end = sdf.format(c.getTime());
		c.add(Calendar.DAY_OF_MONTH,-3);
		String start = sdf.format(c.getTime());
		return find("from Attend as a where a.employee=?0 and" + "a.type!=?1 and a.dutyDay between ?2 and ?3",emp,attendType,start,end);
	}
}