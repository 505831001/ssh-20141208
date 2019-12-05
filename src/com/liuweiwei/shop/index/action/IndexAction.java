package com.liuweiwei.shop.index.action;

import com.liuweiwei.shop.category.service.CategoryService;
import com.liuweiwei.shop.category.vo.Category;
import com.liuweiwei.shop.product.service.ProductService;
import com.liuweiwei.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class IndexAction extends ActionSupport {
    private CategoryService categoryService;
    private ProductService productService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public String execute() {
        List<Category> cList = categoryService.findAll();
        ActionContext.getContext().getSession().put("cList", cList);
        List<Product> hList = productService.findHot();
        ActionContext.getContext().getValueStack().set("hList", hList);
        List<Product> nList = productService.findNew();
        ActionContext.getContext().getValueStack().set("nList", nList);
        return "index";
    }
}
