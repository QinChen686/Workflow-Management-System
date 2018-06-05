package org.qc.hrsystem.service.impl;
import java.util.*;
//import java.sql.Date;
import java.text.*;
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

    public void setAppDao(ApplicationDao appDao)
    {
        this.appDao = appDao;
    }

    public void setAttendDao(AttendDao attendDao)
    {
        this.attendDao = attendDao;
    }

    public void setTypeDao(AttendTypeDao typeDao)
    {
        this.typeDao = typeDao;
    }

    public void setCheckDao(CheckBackDao checkDao)
    {
        this.checkDao = checkDao;
    }

    public void setEmpDao(EmployeeDao empDao)
    {
        this.empDao = empDao;
    }

    public void setMgrDao(ManagerDao mgrDao)
    {
        this.mgrDao = mgrDao;
    }

    public void setPayDao(PaymentDao payDao)
    {
        this.payDao = payDao;
    }

    /**
     * 自动打卡，周一到周五，早上7：00为每个员工插入旷工记录
     */
    public void autoPunch()
    {
        System.out.println("自动插入旷工记录");
        List<Employee> emps = empDao.findAll(Employee.class);
        // 获取当前时间
        String dutyDay = new java.sql.Date(
                System.currentTimeMillis()).toString();
        for (Employee e : emps)
        {
            // 获取旷工对应的出勤类型
            AttendType atype = typeDao.get(AttendType.class , 6);
            Attend a = new Attend();
            a.setDutyDay(dutyDay);
            a.setType(atype);
            // 如果当前时间是是早上，对应于上班打卡
            if (Calendar.getInstance()
                    .get(Calendar.HOUR_OF_DAY) < AM_LIMIT)
            {
                // 上班打卡
                a.setIsCome(true);
            }
            else
            {
                // 下班打卡
                a.setIsCome(false);
            }
            a.setEmployee(e);
            attendDao.save(a);
        }
    }

    public int validLogin(Manager mgr)
    {
       if(mgrDao.findByNameAndPass(mgr)!=null)
       {
           return LOGIN_MGR;
       }
       else if(empDao.findByNameAndPass(mgr)!=null)
       {
           return LOGIN_EMP;
       }
       return LOGIN_FAIL;
    }

    public int validPunch(Employee emp, String dutyDay)
    {
        List<Attend> attends = attendDao.findByEmpAndDutyDay(emp,dutyDay);
        if(attends==null||attends.size()<1)
        {
            return NO_PUNCH;
        }
        if(attends.size()==1&&attends.get(0).getIsCome()==true&&attends.get(0).getPunchTime()==null)
        {
            return COME_PUNCH;
        }
        if(attends.size()==1&&attends.get(0).getPunchTime()==null)
        {
            return LEAVE_PUNCH;
        }
        if(attends.size()==2&&attends.get(0)==null&&attends.get(1)==null)
        {
            return BOTH_PUNCH;
        }
        if(attends.size()==2&&attends.get(1).getPunchTime()==null)
        {
            return LEAVE_PUNCH;
        }
        return NO_PUNCH;
    }

    public List<PaymentBean> empSalary(String emp)
    {
        List<PaymentBean> paymentBeanList = new ArrayList<>();
        Employee employee = empDao.findByName(emp);
        Set<Payment> paymentSet = employee.getPayments();
        for(Payment pay:paymentSet)
        {
            paymentBeanList.add(new PaymentBean(pay.getMonth(),pay.getAmount()));
        }
        return paymentBeanList;
    }

    public List<AttendBean> unAttend(String emp)
    {
        Employee employee = empDao.findByName(emp);
        AttendType type = typeDao.get(AttendType.class,1);
        List<Attend> attendList=attendDao.findByEmpUnAttend(employee,type);
        List<AttendBean> attendBeans=new ArrayList<>();
        for(Attend attend:attendList)
        {
            attendBeans.add(new AttendBean(attend.getId(),attend.getDutyDay(),attend.getType().getName(),attend.getPunchTime()));
        }
        return attendBeans;
    }

    public boolean addApplication(int attId, int typeId, String reason)
    {
        Application app = new Application();
        Attend attend = attendDao.get(Attend.class,attId);
        app.setAttend(attend);
        AttendType type=typeDao.get(AttendType.class,typeId);
        app.setType(type);
        app.setReason(reason);
        appDao.save(app);
        return true;
    }

    public List<AttendType> getAllType()
    {
        return typeDao.findAll(AttendType.class);
    }

    public int punch(String user, String dutyDay, boolean isCome)
    {
        Employee emp=empDao.findByName(user);
        Attend attend=attendDao.findByEmpAndDutyDayAndCome(emp,dutyDay,isCome);
        if(attend==null)
        return PUNCH_FAIL;
        if(attend.getPunchTime()!=null)
            return PUNCHED;

        int punchValidRes = validPunch(emp,dutyDay);
        int punchHour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if((punchValidRes==COME_PUNCH||punchValidRes==BOTH_PUNCH)&&isCome==true)
        {
            if(punchHour<COME_LIMIT)
            {
                attend.setType(typeDao.get(AttendType.class,1));
            }
            else if(punchHour<=COME_LIMIT)
            {
                attend.setType(typeDao.get(AttendType.class,4));
            }
        }
        if((punchValidRes==LEAVE_PUNCH||punchValidRes==BOTH_PUNCH)&&isCome==false)
        {
            attend.setPunchTime(new Date());
            if(punchHour>=LEAVE_LIMIT)
            {
                attend.setType(typeDao.get(AttendType.class,1));
            }
            if(punchHour>=EARLY_LIMIT)
            {
                attend.setType((typeDao.get(AttendType.class,5)));
            }
        }
        attendDao.update(attend);
        return PUNCH_SUCC;
    }

    public void autoPay()
    {
        //每个月第一天自动结算
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c =Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,-15);
        String payMonth=sdf.format(c);
        List<Employee> employeeList=empDao.findAll(Employee.class);
        for(Employee emp:employeeList)
        {
            Payment pay = new Payment();
            List<Attend> attends = attendDao.findByEmpAndMonth(emp,payMonth);
            double amount = emp.getSalary();
            for(Attend attend:attends)
            {
                amount += attend.getType().getAmerce();
            }
            pay.setMonth(payMonth);
            pay.setAmount(amount);
            pay.setEmployee(emp);
            payDao.save(pay);
        }
    }
}