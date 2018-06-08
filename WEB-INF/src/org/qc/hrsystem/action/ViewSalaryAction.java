package org.qc.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import org.qc.hrsystem.action.base.EmpBaseAction;
import org.qc.hrsystem.vo.PaymentBean;


import java.util.List;

public class ViewSalaryAction extends EmpBaseAction
{
    private List<PaymentBean> salaryBeans;

    public void setSalaryBeans(List<PaymentBean> salaryBeans)
    {
        this.salaryBeans = salaryBeans;
    }

    public List<PaymentBean> getSalaryBeans()
    {
        return salaryBeans;
    }

    public String execute()
    {
        ActionContext ctx=ActionContext.getContext();
        String user=(String) ctx.getSession().get(WebConstant.USER);
        setSalaryBeans(mgr.empSalary(user));
        return SUCCESS;
    }
}
