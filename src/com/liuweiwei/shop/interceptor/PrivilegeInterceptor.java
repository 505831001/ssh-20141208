package com.liuweiwei.shop.interceptor;

import com.liuweiwei.shop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        AdminUser adminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
        if (adminUser != null) {
            return actionInvocation.invoke();
        } else {
            ActionSupport support = (ActionSupport) actionInvocation.getAction();
            support.addActionError("您还没有登录!没有权限访问!");
            return ActionSupport.LOGIN;
        }
    }

}
