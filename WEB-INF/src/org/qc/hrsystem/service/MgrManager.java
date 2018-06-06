package org.qc.hrsystem.service;
import java.util.*;
import org.qc.hrsystem.domain.*;
import org.qc.hrsystem.exception.HrException;
import org.qc.hrsystem.vo.*;

public interface MgrManager
{
    public void addEmp(Employee emp, String mgr) throws HrException;

    public void check(int appId, String mgrName, boolean result);

    public List<SalaryBean> getSalaryByMgr(String mgr);

    public List<AppBean> getAppByMgr(String mgr);

    public List<EmpBean> getEmpByMgr(String mgr);


}

