package com.liuweiwei.shop.adminuser.dao;

import com.liuweiwei.shop.adminuser.vo.AdminUser;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class AdminUserDao extends HibernateDaoSupport {

    public AdminUser login(AdminUser adminUser) {
        String hql = "from u_user where name = ? and password = ?";
        List<AdminUser> list = this.getHibernateTemplate().find(hql, adminUser.getUsername(), adminUser.getPassword());
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
