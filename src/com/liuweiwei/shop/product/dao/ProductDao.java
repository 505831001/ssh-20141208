package com.liuweiwei.shop.product.dao;

import com.liuweiwei.shop.product.vo.Product;
import com.liuweiwei.shop.utils.PageHibernateCallback;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class ProductDao extends HibernateDaoSupport {

    public List<Product> findHot() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
        criteria.add(Restrictions.eq("is_hot", 1));
        criteria.addOrder(Order.desc("pdate"));
        List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
        return list;
    }

    public List<Product> findNew() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
        criteria.addOrder(Order.desc("pdate"));
        List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
        return list;
    }

    public Product findByPid(Integer pid) {
        return this.getHibernateTemplate().get(Product.class, pid);
    }

    public int findCountCid(Integer cid) {
        String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
        List<Long> list = this.getHibernateTemplate().find(hql, cid);
        if (list != null && list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    public List<Product> findByPageCid(Integer cid, int begin, int limit) {
        String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
        List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    public int findCountCsid(Integer csid) {
        String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
        List<Long> list = this.getHibernateTemplate().find(hql, csid);
        if (list != null && list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
        String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
        List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    public int findCount() {
        String hql = "select count(*) from Product";
        List<Long> list = this.getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    // 后台查询所有商品的方法
    public List<Product> findByPage(int begin, int limit) {
        String hql = "from Product order by pdate desc";
        List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    public void save(Product product) {
        this.getHibernateTemplate().save(product);
    }

    public void delete(Product product) {
        this.getHibernateTemplate().delete(product);
    }

    public void update(Product product) {
        this.getHibernateTemplate().update(product);
    }

}
