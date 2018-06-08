package org.qc.hrsystem.action.authority;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.qc.hrsystem.action.WebConstant;

public class MgrAuthorityInterceptor extends AbstractInterceptor
{
    public String intercept(ActionInvocation invocation) throws Exception
    {
        ActionContext ctx = ActionContext.getContext();
        String level = (String) ctx.getSession().get(WebConstant.LEVEL);
        if(level!=null&&(level.equals(WebConstant.MGR_LEVEL)))
        {
            return invocation.invoke();
        }
        return Action.LOGIN;
    }
}
