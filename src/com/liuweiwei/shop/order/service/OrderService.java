package com.liuweiwei.shop.order.service;

import com.liuweiwei.shop.order.dao.OrderDao;
import com.liuweiwei.shop.order.vo.Order;
import com.liuweiwei.shop.order.vo.OrderItem;
import com.liuweiwei.shop.utils.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class OrderService {
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void save(Order order) {
        orderDao.save(order);
    }

    public PageBean<Order> findByUid(Integer uid, Integer page) {
        PageBean<Order> pageBean = new PageBean<Order>();
        pageBean.setPage(page);

        int limit = 5;
        pageBean.setLimit(limit);
        int totalCount = 0;
        totalCount = orderDao.findCountByUid(uid);
        pageBean.setTotalCount(totalCount);
        int totalPage = 0;
        if (totalCount % limit == 0) {
            totalPage = totalCount / limit;
        } else {
            totalPage = totalCount / limit + 1;
        }
        pageBean.setTotalPage(totalPage);
        int begin = (page - 1) * limit;
        List<Order> list = orderDao.findPageByUid(uid, begin, limit);
        pageBean.setList(list);
        return pageBean;
    }

    public Order findByOid(Integer oid) {
        return orderDao.findByOid(oid);
    }

    public void update(Order currOrder) {
        orderDao.update(currOrder);
    }

    public PageBean<Order> findAll(Integer page) {
        PageBean<Order> pageBean = new PageBean<Order>();
        pageBean.setPage(page);
        int limit = 10;
        pageBean.setLimit(limit);
        int totalCount = orderDao.findCount();
        pageBean.setTotalCount(totalCount);
        int totalPage = 0;
        if (totalCount % limit == 0) {
            totalPage = totalCount / limit;
        } else {
            totalPage = totalCount / limit + 1;
        }
        pageBean.setTotalPage(totalPage);
        int begin = (page - 1) * limit;
        List<Order> list = orderDao.findByPage(begin, limit);
        pageBean.setList(list);
        return pageBean;
    }

    public List<OrderItem> findOrderItem(Integer oid) {
        return orderDao.findOrderItem(oid);
    }

}
