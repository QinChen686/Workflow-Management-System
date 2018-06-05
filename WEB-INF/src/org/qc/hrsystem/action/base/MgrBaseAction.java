package org.qc.hrsystem.action.base;

import com.opensymphony.xwork2.ActionSupport;
import org.qc.hrsystem.service.MgrManager;
public class MgrBaseAction extends ActionSupport
{
    private MgrManager mgr;
    public void setMgr(MgrManager mgr)
    {
        this.mgr=mgr;
    }
}
