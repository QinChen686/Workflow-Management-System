package org.qc.common.Dao;
import java.util.List;
import java.io.Serializable;

public interface BaseDao<T>
{
	T get(Class<T> entityClazz, Serializable id);
	Serializable save(T entity);
	void update(T entity);
	void delete(Class<T> entity, Serializable id);
	List<T> findAll(Class<T> entityClazz);
	long findCount(Class<T> entityClazz);
}