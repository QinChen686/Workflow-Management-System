package org.qc.hrsystem.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import org.qc.hrsystem.action.base.MgrBaseAction;
import org.qc.hrsystem.vo.EmpBean;
import org.qc.hrsystem.domain.Manager;
public class ViewEmpAction extends MgrBaseAction
{
    private List emps;
    public void setEmps(List emps)
    {
        this.emps=emps;
    }
    public List getEmps()
    {
        return this.emps;
    }
    public String execute()
    {
        ActionContext ctx=ActionContext.getContext();
        setEmps(mgr.getEmpByMgr((String)ctx.getSession().get(WebConstant.USER)));
        return SUCCESS;
    }
}
