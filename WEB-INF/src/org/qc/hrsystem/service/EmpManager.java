package org.qc.hrsystem.service;

import java.util.*;

import org.qc.hrsystem.vo.*;
import org.qc.hrsystem.domain.*;

public interface EmpManager
{
    public static final int LOGIN_FAIL=0;
    public static final int LOGIN_EMP=1;
    public static final int LOGIN_MGR=2;

    public static final int NO_PUNCH=0;
    public static final int COME_PUNCH=1;
    public static final int LEAVE_PUNCH=2;
    public static final int BOTH_PUNCH=3;

    public static final int PUNCH_FAIL=0;
    public static final int PUNCHED=1;
    public static final int PUNCH_SUCC=2;

    public static final int AM_LIMIT=11;
    public static final int COME_LIMIT=9;
    public static final int LATE_LIMIT=11;
    public static final int LEAVE_LIMIT=18;
    public static final int EARLY_LIMIT=16;

    public void autoPunch();

    public int validLogin(Manager mgr);

    public int validPunch(String emp0, String dutyDay);

    public List<PaymentBean> empSalary(String emp);

    public List<AttendBean> unAttend(String emp);

    public boolean addApplication(int attId, int typeId, String reason);

    public List<AttendType> getAllType();

    public int punch(String user, String dutyDay, boolean isCome);

    public void autoPay();
}
