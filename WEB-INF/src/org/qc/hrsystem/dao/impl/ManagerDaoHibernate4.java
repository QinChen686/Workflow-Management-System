package  org.qc.hrsystem.dao.impl;
import java.util.*;
import org.qc.hrsystem.dao.ManagerDao;
import org.qc.hrsystem.domain.Manager;
import org.qc.common.Dao.impl.BaseDaoHibernate4;

public class ManagerDaoHibernate4 extends BaseDaoHibernate4<Manager> implements ManagerDao
{
	public List<Manager> findByNameAndPass(Manager mgr)
	{
		return find("select a from Manager as a where a.name=?0 and a.pass=?1",mgr.getName(),mgr.getPass());
	}
	
	public Manager findByName(String name)
	{
		List<Manager> mgrs=find("select a from Manager as a where a.name=?0",name);
		if(mgrs!=null&&mgrs.size()>=1)
		{
			return mgrs.get(0);
		}
		return null;
	}
}