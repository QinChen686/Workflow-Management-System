package org.qc.hrsystem.dao;
import java.util.*;
import org.qc.common.Dao.BaseDao;
import org.qc.hrsystem.domain.*;

public interface ApplicationDao extends BaseDao<Application>
{
	/**
	*根据员工查询未处理的异动申请
	*@param emp 需要查询的员工
	*@return 该员工对应的未处理的异动申请
	*/
	List<Application> findByEmp(Employee emp);

}