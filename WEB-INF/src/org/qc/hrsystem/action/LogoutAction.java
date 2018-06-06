package org.qc.hrsystem.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutAction extends ActionSupport implements ServletRequestAware
{
    private HttpServletRequest request;
    public void setServletRequest(HttpServletRequest request)
    {
        this.request=request;
    }
    public String execute()
    {
        HttpSession session=request.getSession();
        session.invalidate();
        return SUCCESS;
    }
}
