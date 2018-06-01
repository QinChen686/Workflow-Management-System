package org.qc.hrsystem.dao;
import java.util.*;
import org.qc.common.Dao.BaseDao;
import org.qc.hrsystem.domain.*;

public interface AttendDao extends BaseDao<Attend>
{
	/**查询员工某个月的考勤
	*@Param emp 员工
	*@param month 月份，格式为2012-02样式的字符串
	*@return 该员工该月的全部考勤记录
	*/
	List<Attend> findByEmpAndMonth(Employee emp, String month);
	/**查询员工某天的考勤
	*@param emp 员工
	*@param dutyDay 日期
	*@return 该员工当天的考勤记录
	*/
	List<Attend> findByEmpAndDutyDay(Employee emp, String dutyDay);
	
	Attend findByEmpAndDutyDayAndCome(Employee emp, String dutyDay, boolean isCome);
	
	List<Attend> findByEmpUnAttend(Employee emp, AttendType type);
}