package org.qc.hrsystem.action;

import org.qc.hrsystem.action.base.EmpBaseAction;

public class ProcessAppAction extends EmpBaseAction
{
    private int attendId;
    private String reason;
    private int typeId;

    public void setAttendId(int attendId)
    {
        this.attendId = attendId;
    }
    public int getAttendId()
    {
        return attendId;
    }
    public void setReason(String reason)
    {
        this.reason = reason;
    }
    public String getReason()
    {
        return reason;
    }
    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
    }
    public int getTypeId()
    {
        return typeId;
    }
    public String execute()
    {
        boolean res=mgr.addApplication(attendId,typeId,reason);
        if(res)
        {
            addActionMessage("申请成功，等待经理审阅");
        }
        else
        {
            addActionMessage("申请失败，请不要重新申请");
        }
        return SUCCESS;
    }
}
