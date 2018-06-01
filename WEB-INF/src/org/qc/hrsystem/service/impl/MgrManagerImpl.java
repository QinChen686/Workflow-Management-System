package org.qc.hrsystem.service.impl;
import org.qc.hrsystem.dao.*;
import org.qc.hrsystem.service.MgrManager;
import org.qc.hrsystem.exception.HrException;
import org.qc.hrsystem.domain.*;
import org.qc.hrsystem.vo.*;
import java.util.*;


public class MgrManagerImpl implements MgrManager
{
    private ApplicationDao appDao;
    private AttendDao attendDao;
    private AttendTypeDao typeDao;
    private CheckBackDao checkDao;
    private PaymentDao payDao;
    private EmployeeDao empDao;
    private ManagerDao mgrDao;

    //依赖注入getter、setter 方法
    public void setAppDao(ApplicationDao appDao)
    {
        this.appDao=appDao;
    }

    public ApplicationDao getAppDao()
    {
        return this.appDao;
    }

    public void setAttendDao(AttendDao attendDao)
    {
        this.attendDao=attendDao;
    }

    public AttendDao getAttendDao()
    {
        return this.attendDao;
    }

    public void setTypeDao(AttendTypeDao typeDao)
    {
        this.typeDao=typeDao;
    }

    public AttendTypeDao getTypeDao()
    {
        return this.typeDao;
    }

    public void setCheckDao(CheckBackDao checkDao)
    {
        this.checkDao=checkDao;
    }

    public CheckBackDao getCheckDao()
    {
        return this.checkDao;
    }

    public void setEmpDao(EmployeeDao empDao)
    {
        this.empDao=empDao;
    }

    public EmployeeDao getEmpDao()
    {
        return this.empDao;
    }

    public void setMgrDao(ManagerDao mgrDao)
    {
        this.mgrDao=mgrDao;
    }

    public ManagerDao getMgrDao()
    {
        return this.mgrDao;
    }

    public void setPayDao(PaymentDao payDao)
    {
        this.payDao=payDao;
    }

    public PaymentDao getPayDao()
    {
        return this.payDao;
    }

    public void addEmp(Employee emp, String Mgr) throws HrException
    {

    }

    public void check(int appId, String Mgr, boolean result) throws HrException
    {

    }

    public List<AppBean> getAppByMgr(String mgr) throws HrException
    {

    }

    public List<EmpBean> getEmpByMgr(String mgr) throws HrException
    {

    }

    public List<SalaryBean> getSalaryByMgr(String mgr) throws HrException
    {

    }
}