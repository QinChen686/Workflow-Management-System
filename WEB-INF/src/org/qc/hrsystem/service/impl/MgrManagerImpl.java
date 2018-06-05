package org.qc.hrsystem.service.impl;
import org.qc.hrsystem.dao.*;
import org.qc.hrsystem.service.MgrManager;
import org.qc.hrsystem.exception.HrException;
import org.qc.hrsystem.domain.*;
import org.qc.hrsystem.vo.*;

import java.text.SimpleDateFormat;
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
        Manager mgr=mgrDao.findByName(Mgr);
        if(mgr==null)
        {
           throw new HrException("您是经理吗，或你还未登陆？");
        }
        emp.setManager(mgr);
        empDao.save(emp);
    }

    public void check(int appId, String Mgr, boolean result) throws HrException
    {
        Application app=appDao.get(Application.class, appId);
        CheckBack check=new CheckBack();
        Manager mgr=mgrDao.findByName(Mgr);
        if(mgr==null)
        {
            throw new HrException("你是经理吗，或你还未登录？");
        }
        check.setManager(mgr);
        check.setApp(app);
        if(result)
        {
            check.setResult(true);
            app.setResult(true);
            appDao.update(app);
            Attend att=app.getAttend();
            att.setType(app.getType());
            attendDao.update(att);
        }
        else {
            check.setResult(false);
            app.setResult(true);
            appDao.update(app);
        }
        checkDao.save(check);
    }

    public List<AppBean> getAppByMgr(String mgr) throws HrException
    {
        Manager manager=mgrDao.findByName(mgr);
        if(manager==null)
        {
            throw new HrException("你不是经理，或你还未登录");
        }
        Set<Employee> emps=manager.getEmployees();
        if(emps==null || emps.size()<1)
        {
            throw new HrException("你还没有员工！");
        }
        List<AppBean> appBeans=new ArrayList<>();
        for(Employee emp:emps)
        {
            List<Application> apps=appDao.findByEmp(emp);
            if(apps!=null && apps.size()>=1)
            {
                for(Application app:apps)
                {
                    if(!app.getResult())
                    {
                        appBeans.add(new AppBean(app.getId(),emp.getName(),app.getAttend().getType().getName(),app.getType().getName(),app.getReason()));
                    }

                }
            }
        }
        return appBeans;
    }


    public List<EmpBean> getEmpByMgr(String mgr) throws HrException
    {
        Manager manager = mgrDao.findByName(mgr);
        if(manager==null)
        {
            throw new HrException("你不是经理或你还未登录");
        }
        List<EmpBean> empBeans = new ArrayList<>();
        Set<Employee> emps = manager.getEmployees();
        if(emps==null||emps.size()<1)
        {
            throw new HrException("你的部门没有员工");
        }
        for(Employee emp:emps)
        {
            empBeans.add(new EmpBean(emp.getName(),emp.getPass(),emp.getSalary()));
        }
        return empBeans;
    }

    /**该经理员工上个月的薪水
     *
     * @param mgr 经理名
     * @return 所有员工的上个月薪水
     * @throws HrException 输入的经理名不是经理的异常
     */
    public List<SalaryBean> getSalaryByMgr(String mgr) throws HrException {
        Manager manager = mgrDao.findByName(mgr);
        if (manager == null) {
            throw new HrException("你不是经理或你还未登录？");
        }
        Set<Employee> emps = manager.getEmployees();
        if (emps == null || emps.size() < 1)
        {
            throw new HrException("你的部门没有员工");
        }
        Calendar c=Calendar.getInstance();
        SimpleDateFormat sft=new SimpleDateFormat("yyyy-MM");
        c.add(Calendar.MONTH,-1);
        String month=sft.format(c.getTime());
        List<SalaryBean> salaryBeans = new ArrayList<> ();
        for(Employee emp:emps)
        {
            Payment pay = payDao.findByEmpAndMonth(emp,month);
            salaryBeans.add(new SalaryBean(emp.getName(),pay.getAmount()));
        }
        return salaryBeans;
    }
}