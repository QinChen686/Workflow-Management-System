package org.qc.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import org.qc.hrsystem.action.base.MgrBaseAction;

public class CheckAppAction extends MgrBaseAction
{
    public int appId;
    public String result;
    public void setAppId(int appId)
    {
        this.appId=appId;
    }
    public int getAppId()
    {
        return this.appId;
    }
    public void setResult(String reslut)
    {
        this.result=result;
    }
    public String getResult()
    {
        return result;
    }
    public String execute() throws Exception
    {
        ActionContext ctx=ActionContext.getContext();
        String managerName = (String) ctx.getSession().get(WebConstant.USER);
        if(result=="pass")
            mgr.check(appId,managerName,true);
        else if(result=="deny")
            mgr.check(appId,managerName,false);
        else
            throw new Exception("参数丢失");
        return SUCCESS;
    }
}
