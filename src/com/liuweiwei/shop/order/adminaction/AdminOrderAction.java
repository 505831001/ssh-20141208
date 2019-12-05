package com.liuweiwei.shop.order.adminaction;

import com.liuweiwei.shop.order.service.OrderService;
import com.liuweiwei.shop.order.vo.Order;
import com.liuweiwei.shop.order.vo.OrderItem;
import com.liuweiwei.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {
    private Order order = new Order();

    public Order getModel() {
        return order;
    }

    private Integer page;

    public void setPage(Integer page) {
        this.page = page;
    }

    private OrderService orderService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }


    public String findAll() {
        PageBean<Order> pageBean = orderService.findAll(page);
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);
        return "findAll";
    }

    public String updateState() {
        Order currOrder = orderService.findByOid(order.getOid());
        currOrder.setState(3);
        orderService.update(currOrder);
        return "updateStateSuccess";
    }

    public String findOrderItem() {
        List<OrderItem> list = orderService.findOrderItem(order.getOid());
        ActionContext.getContext().getValueStack().set("list", list);
        return "findOrderItem";
    }
}
