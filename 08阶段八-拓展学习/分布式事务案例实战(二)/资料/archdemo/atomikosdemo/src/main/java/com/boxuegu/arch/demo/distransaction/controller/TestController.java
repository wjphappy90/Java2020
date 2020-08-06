/*
 * Copyright (c) 博学谷 http://www.boxuegu.com
 *
 */

package com.boxuegu.arch.demo.distransaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jam Fang  https://www.jianshu.com/u/0977ede560d4
 * @version 创建时间：2019/4/16 16:54
 */

@Controller
@RequestMapping("/test")
public class TestController {
    private final String orderSQL = "INSERT INTO `order`(orderid,amount,product) values('22222','9000.00','atomikosdemo苹果笔记本');";
    private final String shippingSQL = "INSERT INTO shipping(orderid,address) VALUES('6666','atomikosdemo 上海市黄浦江畔');";
    @Autowired
    private JdbcTemplate orderJdbcTemplate;
    @Autowired
    private JdbcTemplate shippingJdbcTemplate;

    @Transactional
    @RequestMapping("/test")
    public void test() {
        System.out.println("begin.....");
        orderJdbcTemplate.execute(orderSQL);
        int i=1/0;
        shippingJdbcTemplate.execute(shippingSQL);
        System.out.println("end.....");
    }
}
