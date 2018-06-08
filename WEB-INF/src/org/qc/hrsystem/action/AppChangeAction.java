package org.qc.hrsystem.action;

import org.qc.hrsystem.action.base.EmpBaseAction;
import org.qc.hrsystem.domain.AttendType;

import java.util.List;

public class AppChangeAction extends EmpBaseAction
{
    private List<AttendType> apps;
    public void setApps(List<AttendType> apps)
    {
        this.apps=apps;
    }
    public List<AttendType> getApps()
    {
        return this.apps;
    }
    public String execute()
    {
        setApps(mgr.getAllType());
        return SUCCESS;
    }
}
