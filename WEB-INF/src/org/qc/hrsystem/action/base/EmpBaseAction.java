package org.qc.hrsystem.action.base;

import com.opensymphony.xwork2.ActionSupport;
import org.qc.hrsystem.service.EmpManager;
public class EmpBaseAction extends ActionSupport
{
    EmpManager mgr;
    public void setMgr(EmpManager mgr)
    {
        this.mgr=mgr;
    }
}
