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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author Jam Fang  https://www.jianshu.com/u/0977ede560d4
 * @version 创建时间：2019/4/16 16:54
 */

@Controller
public class TestController {
    private final String orderSQL = "INSERT INTO `order`(orderid,amount,product) values('00001','3000.00','"
            + (new Date().toString().substring(10)) + "');";
    private final String shippingSQL = "INSERT INTO shipping(orderid1,address) VALUES('00001','" +
            (new Date()).toString() + "上海市黄浦江畔');";
    @Autowired
    private JdbcTemplate orderJdbcTemplate;
//    @Autowired
//    private JdbcTemplate shippingJdbcTemplate;

    @RequestMapping("/test")
    @ResponseBody
    @Transactional
    public String test() {
        System.out.println("begin.....");
        orderJdbcTemplate.execute(orderSQL);
        //shippingJdbcTemplate.execute(shippingSQL);
        System.out.println("end.....");
        int i = 1 / 0;
        return "success";
    }
}
