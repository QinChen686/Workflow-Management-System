package org.qc.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import org.qc.hrsystem.action.base.MgrBaseAction;
import org.qc.hrsystem.domain.Employee;
public class AddEmpAction extends MgrBaseAction
{
    private Employee emp;
    public void setEmp(Employee emp)
    {
        this.emp=emp;
    }
    public Employee getEmp()
    {
        return this.emp;
    }
    public String execute()
    {
        ActionContext ctx=ActionContext.getContext();
        mgr.addEmp(emp,(String) ctx.getSession().get(WebConstant.USER));
        addActionMessage("新增员工成功");
        return SUCCESS;
    }
}
