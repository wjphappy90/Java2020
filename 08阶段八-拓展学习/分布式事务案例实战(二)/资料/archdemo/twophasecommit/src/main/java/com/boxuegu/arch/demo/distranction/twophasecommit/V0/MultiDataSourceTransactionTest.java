/*
 * Copyright (c) 博学谷 http://www.boxuegu.com
 *
 */

package com.boxuegu.arch.demo.distranction.twophasecommit.V0;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jam Fang  https://www.jianshu.com/u/0977ede560d4
 * @version 创建时间：2019/4/16 9:06
 */
public class MultiDataSourceTransactionTest {
    @Transactional
    public void addOrder() {
        final String orderSQL = "INSERT INTO `order`(orderid,amount,product) values('00001','3000.00','苹果笔记本');";
        final String shippingSQL = //"INSERT INTO shipping(orderid,address) VALUES('00001','上海市黄浦江畔');";
                "update shipping set address='北京黄浦江畔' where id=1;";//3

    }
}
