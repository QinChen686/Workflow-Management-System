package org.qc.hrsystem.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import org.qc.hrsystem.action.base.MgrBaseAction;

import java.util.List;
public class ViewDeptAction extends MgrBaseAction
{
    //查看当前部门所有员工上个月工资
    private List salaryBeans;
    public void setSalaryBean(List salaryBeans)
    {
        this.salaryBeans=salaryBeans;
    }
    public List getSalaryBeans()
    {
        return this.salaryBeans;
    }
    public String execute()
    {
        ActionContext ctx=ActionContext.getContext();
        String user=(String) ctx.getSession().get(WebConstant.USER);
        setSalaryBean(mgr.getSalaryByMgr(user));
        return SUCCESS;
    }
}
