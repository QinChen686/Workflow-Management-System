package org.qc.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import org.qc.hrsystem.action.base.EmpBaseAction;
import org.qc.hrsystem.vo.AttendBean;

import java.util.List;

public class ViewUnAttend extends EmpBaseAction
{
    private List<AttendBean> unattends;
    public void setUnattends(List unattends)
    {
        this.unattends=unattends;
    }
    public List<AttendBean> getUnattends()
    {
        return this.unattends;
    }
    public String execute()
    {
        ActionContext ctx=ActionContext.getContext();
        String user=(String) ctx.getSession().get(WebConstant.USER);
        setUnattends(mgr.unAttend(user));
        return SUCCESS;
    }
}
