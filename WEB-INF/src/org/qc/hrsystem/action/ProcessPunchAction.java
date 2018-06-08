package org.qc.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import org.qc.hrsystem.action.base.EmpBaseAction;
import org.qc.hrsystem.service.impl.EmpManagerImpl;

public class ProcessPunchAction extends EmpBaseAction
{
    public String come()
    {
        return process(true);
    }
    public String leave()
    {
        return process(false);
    }
    public String process(boolean isCome)
    {
        String dutyDay=new java.sql.Date(System.currentTimeMillis()).toString();
        ActionContext ctx = ActionContext.getContext();
        String user=(String) ctx.getSession().get(WebConstant.USER);
        int res=mgr.punch(user,dutyDay,isCome);
        switch(res)
        {
            case EmpManagerImpl.PUNCH_FAIL:
                addActionMessage("打卡失败");
                break;
            case EmpManagerImpl.PUNCHED:
                addActionMessage("已打卡，请勿重新打卡");
                break;
            case EmpManagerImpl.PUNCH_SUCC:
                addActionMessage("打卡成功");
                break;
        }
        return SUCCESS;
    }
}
