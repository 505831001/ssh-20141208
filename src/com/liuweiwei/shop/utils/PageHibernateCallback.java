package com.liuweiwei.shop.utils;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.List;

public class PageHibernateCallback<T> implements HibernateCallback<List<T>> {

    private String hql;
    private Object[] params;
    private int startIndex;
    private int pageSize;

    public PageHibernateCallback(String hql, Object[] params, int startIndex, int pageSize) {
        super();
        this.hql = hql;
        this.params = params;
        this.startIndex = startIndex;
        this.pageSize = pageSize;
    }

    public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
        Query query = session.createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        query.setFirstResult(startIndex);
        query.setMaxResults(pageSize);
        return query.list();
    }

}
