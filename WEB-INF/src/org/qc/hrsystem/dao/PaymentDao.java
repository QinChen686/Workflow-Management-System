package org.qc.hrsystem.dao;
import java.util.*;
import org.qc.hrsystem.domain.*;
import org.qc.common.Dao.BaseDao;

public interface PaymentDao extends BaseDao<Payment>
{
	List<Payment> findByEmp(Employee emp);
	
	Payment findByEmpAndMonth(Employee emp, String payMonth);
}