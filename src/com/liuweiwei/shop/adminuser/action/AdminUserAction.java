package com.liuweiwei.shop.adminuser.action;

import com.liuweiwei.shop.adminuser.service.AdminUserService;
import com.liuweiwei.shop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {
    private AdminUser adminUser = new AdminUser();
    public AdminUser getModel() {
        return adminUser;
    }
    private AdminUserService adminUserService;

    public void setAdminUserService(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    public String login() {
        AdminUser existAdminUser = adminUserService.login(adminUser);
        if (existAdminUser == null) {
            this.addActionError("用户名或密码错误!");
            return "loginFail";
        } else {
            ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
            return "loginSuccess";
        }
    }
}
