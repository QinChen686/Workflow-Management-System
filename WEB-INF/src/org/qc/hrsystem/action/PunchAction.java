package org.qc.hrsystem.action;

import com.opensymphony.xwork2.ActionContext;
import org.qc.hrsystem.action.base.EmpBaseAction;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PunchAction extends EmpBaseAction
{
    private int punchIsValid;

    public void setPunchIsValid(int punchIsValid)
    {
        this.punchIsValid = punchIsValid;
    }
    public int getPunchIsValid()
    {
        return punchIsValid;
    }
    public String execute()
    {
        ActionContext ctx = ActionContext.getContext();
        String user=(String) ctx.getSession().get(WebConstant.USER);
        Calendar c=Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String punchDate=sdf.format(c);
        setPunchIsValid(mgr.validPunch(user,punchDate));
        return SUCCESS;
    }
}
