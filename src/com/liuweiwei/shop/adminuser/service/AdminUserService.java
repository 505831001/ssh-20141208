package com.liuweiwei.shop.adminuser.service;

import com.liuweiwei.shop.adminuser.dao.AdminUserDao;
import com.liuweiwei.shop.adminuser.vo.AdminUser;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AdminUserService {
    private AdminUserDao adminUserDao;

    public void setAdminUserDao(AdminUserDao adminUserDao) {
        this.adminUserDao = adminUserDao;
    }

    public AdminUser login(AdminUser adminUser) {
        return adminUserDao.login(adminUser);
    }
}
