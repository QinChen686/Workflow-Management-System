package org.qc.hrsystem.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import org.qc.hrsystem.action.base.MgrBaseAction;
public class VeiwAppAction extends MgrBaseAction
{
    private List appBeans;
    public void setAppBeans(List appBeans)
    {
        this.appBeans=appBeans;
    }
    public List getAppBeans()
    {
        return this.appBeans;
    }
    public String execute()
    {
        ActionContext ctx = ActionContext.getContext();
        setAppBeans(mgr.getAppByMgr((String)ctx.get(WebConstant.USER)));
        return SUCCESS;
    }
}
