package org.qc.common.Dao.impl;
import java.util.List;
import java.io.Serializable;
import org.qc.common.Dao.*;
import org.hibernate.*;

public class BaseDaoHibernate4<T> implements BaseDao<T>
{
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	public SessionFactory getSessionFactory()
	{
		return this.sessionFactory;
	}
	
	@SuppressWarnings("unchecks")
	public T get(Class<T> entityClazz, Serializable id)
	{
		return (T)getSessionFactory().getCurrentSession().get(entityClazz, id);
	}
	
	@SuppressWarnings("unchecks")
	public Serializable save(T entity)
	{
		return getSessionFactory().getCurrentSession().save(entity);
	}
	
	@SuppressWarnings("unchecks")
	public void update(T entity)
	{
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}
	
	@SuppressWarnings("unchecks")
	public void delete(T entity)
	{
		getSessionFactory().getCurrentSession().delete(entity);
	}
	
	@SuppressWarnings("unchecks")
	public void delete(Class<T> entityClazz, Serializable id)
	{
		getSessionFactory().getCurrentSession().createQuery("delete"+entityClazz.getSimpleName()+"en where en.id=?0").setParameter("0",id).executeUpdate();
		
	}	
	
	@SuppressWarnings("unchecks")
	public List<T> findAll(Class<T> entityClazz)
	{
		return find("select en from" + entityClazz.getSimpleName()+"en");
	}
	
	@SuppressWarnings("unchecks")
	public long findCount(Class<T> entityClazz)
	{
		List<?> l=find("select count(*) from" + entityClazz.getSimpleName());
		if(l!=null&&l.size()==1)
		{
			Object obj=l.get(0);
			return (long) obj;
		}
		return 0;
	}
	
	@SuppressWarnings("unchecks")
	protected List<T> find(String hql)
	{
		return (List<T>) getSessionFactory().getCurrentSession().createQuery(hql).list();
	}
	
	@SuppressWarnings("unchecks")
	protected List<T> find(String hql, Object... params)
	{
		Query query=getSessionFactory().getCurrentSession().createQuery(hql);
		for(int i=0,len=params.length;i<len;i++)
		{
			query.setParameter(i+"",params[i]);
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecks")
	protected List<T> findByPage(String hql, int pageNo, int pageSize, Object... params)
	{
		Query query=getSessionFactory().getCurrentSession().createQuery(hql);
		for(int i=0,len=params.length;i<len;i++)
		{
			query.setParameter(i+"",params[i]);
		}
		return query.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
	}
	
	@SuppressWarnings("unchecks")
	protected List<T> findByPage(String hql, int pageNo, int pageSize)
	{
		return getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
	}
}

