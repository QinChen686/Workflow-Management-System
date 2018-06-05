package org.qc.hrsystem.service.impl;
import java.util.*;
import java.sql.Date;
import org.qc.hrsystem.dao.*;
import org.qc.hrsystem.domain.*;
import org.qc.hrsystem.vo.*;
import org.qc.hrsystem.service.EmpManager;

public class EmpManagerImpl implements EmpManager
{
    private ApplicationDao appDao;
    private AttendDao attendDao;
    private AttendTypeDao typeDao;
    private CheckBackDao checkDao;
    private EmployeeDao empDao;
    private ManagerDao mgrDao;
    private PaymentDao payDao;

    //setter and getter function
    public void setAppDao(ApplicationDao appDao)
    {
        this.appDao=appDao;
    }
    public ApplicationDao getAppDao()
    {
        return appDao;
    }
    public void setAttendDao(AttendDao attendDao)
    {
        this.attendDao=attendDao;
    }
    public AttendDao getAttendDao(AttendDao attendDao)
    {
        return attendDao;
    }
    public void setType(AttendTypeDao typeDao)
    {
        this.typeDao=typeDao;
    }
    public AttendTypeDao getType()
    {
        return typeDao;
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

    public void autoPunch()
    {
        List<Employee> empList=empDao.findAll(Employee.class);
        for(Employee emp:empList)
        {
            Attend attend=new Attend();
            String dutyDay=new java.sql.Date(System.currentTimeMillis()).toString();
            attend.setDutyday(dutyDay);
            attend.setEmployee(emp);
            if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<AM_LIMIT)
            {
                attend.setIsCome(true);
            }
            else
                attend.setIsCome(false);
            attend.setType(typeDao.get(AttendType.class,6));
            attendDao.save(attend);
        }
    }

    public int validLogin(Employee emp)
    {

    }

    public int validPunch(Employee emp, String dutyDay)
    {

    }

    public List<PaymentBean> empSalary(String emp)
    {

    }

    public List<AttendBean> unAttend(String emp)
    {

    }

    public void addApplication(int attId, int typeId, String reason)
    {

    }

    public List<AttendType> getAllType()
    {

    }

    public int punch(String user, String dutyDay, boolean isCome)
    {

    }

    public void autoPay();
    {

    }
}



