package org.qc.hrsystem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;
import org.qc.hrsystem.action.base.EmpBaseAction;
import org.qc.hrsystem.domain.Manager;
import org.qc.hrsystem.service.EmpManager;

public class LoginAction extends EmpBaseAction {
    private final String EMP_RESULT="emp";
    private final String MGR_RESULT="mgr";

    private Manager manager;
    private String verCode;

    public void setManager(Manager manager) {
        this.manager = manager;
    }
    public Manager getManager()
    {
        return this.manager;
    }
    public void setVerCode(String verCode)
    {
        this.verCode=verCode;
    }
    public String getVerCode()
    {
        return this.verCode;
    }

    public String execute() throws Exception
    {
        ActionContext ctx = ActionContext.getContext();
        String verCode2 = (String) ctx.getSession().get("rand");
        if(verCode.equalsIgnoreCase(verCode2))
        {
            if(mgr.validLogin(manager)==EmpManager.LOGIN_EMP)
            {
                ctx.getSession().put(WebConstant.USER,manager.getName());
                ctx.getSession().put(WebConstant.LEVEL,WebConstant.EMP_LEVEL);
                addActionMessage("您已登陆成功");
                return EMP_RESULT;
            }
            if(mgr.validLogin(manager)==EmpManager.LOGIN_EMP)
            {
                ctx.getSession().put(WebConstant.USER,manager.getName());
                ctx.getSession().put(WebConstant.USER,WebConstant.MGR_LEVEL);
                addActionMessage("您已登陆成功");
                return MGR_RESULT;
            }
            else
            {
                addActionMessage("账号或密码错误");
                return ERROR;
            }

        }
        else
        {
            addActionMessage("验证码错误");
            return ERROR;
        }
    }
}
