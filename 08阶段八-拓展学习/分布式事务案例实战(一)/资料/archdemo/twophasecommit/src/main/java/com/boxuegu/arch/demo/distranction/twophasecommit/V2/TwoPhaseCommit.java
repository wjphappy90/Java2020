/*
 * Copyright (c) 博学谷 http://www.boxuegu.com
 *
 */

package com.boxuegu.arch.demo.distranction.twophasecommit.V2;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlXid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Jam Fang  https://www.jianshu.com/u/0977ede560d4
 * @version 创建时间：2019/4/14 16:44
 */
@Component
@Order(2)
public class TwoPhaseCommit implements ApplicationRunner {
    //订单的XA数据源
    @Autowired
    DruidXADataSource orderXADataSource = null;
    //物流XA数据源
    @Autowired
    DruidXADataSource shippingXADataSource = null;

    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.print("建立订单 XA 连接: ");
        XAConnection orderXAConnection = orderXADataSource.getXAConnection();
        System.out.println("成功.");
        System.out.print("建立订单XA resource: ");
        XAResource orderXAResource = orderXAConnection.getXAResource();
        System.out.println("Okay.");
        System.out.print("建立订单数据库连接: ");
        Connection orderConnection = orderXAConnection.getConnection();
        orderConnection.setAutoCommit(false);
        System.out.println("Okay.");

        System.out.print("建立物流 XA 连接: ");
        XAConnection shippingXAConnection = shippingXADataSource.getXAConnection();
        System.out.println("成功.");
        System.out.print("建立物流XA resource: ");
        XAResource shippingXAResource = shippingXAConnection.getXAResource();
        System.out.println("Okay.");
        System.out.print("建立物流数据库连接: ");
        Connection shippingConnection = shippingXAConnection.getConnection();
        shippingConnection.setAutoCommit(false);
        System.out.println("Okay.");


        // 一个统一事务 分成两个事务分支，建立Xid唯一标识事务
        // 参数一:全局事务标识符
        //参数二：事务分支标识符部分
        //参数三：格式标识符。O 指 OSI CCR 格式。
        Xid order_Xid = new MysqlXid("MyGlobal".getBytes(), "order".getBytes(), 0);
        Xid shipping_Xid = new MysqlXid("MyGlobal".getBytes(), "shipping".getBytes(), 0);
        //订单--物流
        final String orderSQL = "INSERT INTO `order`(orderid,amount,product) values('00001','3000.00','苹果笔记本');";
        final String shippingSQL = //"INSERT INTO shipping(orderid,address) VALUES('00001','上海市黄浦江畔');";
                "update shipping set address='北京黄浦江畔' where id=1;";//3
        try {
            //http://tool.oschina.net/uploads/apidocs/jdk-zh/javax/transaction/xa/XAResource.html
            // xa_start负责开启或者恢复一个事务分支，并且管理XID到调用线程
            // xa_end 负责取消当前线程与事务分支的关联
            // xa_prepare负责询问RM 是否准备好了提交事务分支
            // xa_commit通知RM提交事务分支
            // xa_rollback 通知RM回滚事务分支

            //关联订单分支事务sql语句
            System.out.print("关联订单分支事务sql语句....");
            orderXAResource.start(order_Xid, XAResource.TMNOFLAGS);
            Statement orderStatement = orderConnection.createStatement();
            orderStatement.executeUpdate(orderSQL);
            orderXAResource.end(order_Xid, XAResource.TMSUCCESS);
            System.out.println("Okay.");

            //关联物流分支事务sql语句
            System.out.print("关联物流分支事务sql语句....");
            shippingXAResource.start(shipping_Xid, XAResource.TMNOFLAGS);
            Statement shippingStatement = shippingConnection.createStatement();
            shippingStatement.executeUpdate(shippingSQL);
            shippingXAResource.end(shipping_Xid, XAResource.TMSUCCESS);
            System.out.println("Okay.");

            //XA事务准备阶段
            System.out.print("准备订单分支事务 (" +
                    Byte.toString(order_Xid.getGlobalTransactionId()[0]) + ", " +
                    Byte.toString(order_Xid.getBranchQualifier()[0]) + "): ");
            int oderPreResult = orderXAResource.prepare(order_Xid);
            System.out.println("Okay.");

            System.out.print("准备物流分支事务 (" +
                    Byte.toString(shipping_Xid.getGlobalTransactionId()[0]) + ", " +
                    Byte.toString(shipping_Xid.getBranchQualifier()[0]) + "): ");
            int shippingPreResult = shippingXAResource.prepare(shipping_Xid);
            System.out.println("Okay.");

            if (oderPreResult == XAResource.XA_OK && shippingPreResult == XAResource.XA_OK) {
                //都返回OK的话，进行提交阶段
                System.out.print("提交订单分支事务 (" +
                        Byte.toString(order_Xid.getGlobalTransactionId()[0]) + ", " +
                        Byte.toString(order_Xid.getBranchQualifier()[0]) + "): ");
                // void commit(Xid xid,  boolean onePhase) throws XAException
                // 提交 xid 指定的全局事务。
                // 参数：
                // xid - 全局事务标识符
                // onePhase - 如果为 true，则资源管理器应使用单阶段提交协议提交代表 xid 执行的工作。
                orderXAResource.commit(order_Xid, false);
                System.out.println("Okay.");

                System.out.print("提交物流分支事务(" +
                        Byte.toString(shipping_Xid.getGlobalTransactionId()[0]) + ", " +
                        Byte.toString(shipping_Xid.getBranchQualifier()[0]) + "): ");
                shippingXAResource.commit(shipping_Xid, false);
                System.out.println("Okay.");
            } else {//有一个不OK则回滚
                System.out.print("准备阶段应答不全进行回滚 XA branch (" +
                        Byte.toString(order_Xid.getGlobalTransactionId()[0]) + ", " +
                        Byte.toString(order_Xid.getBranchQualifier()[0]) + "): ");
                orderXAResource.rollback(order_Xid);
                System.out.println("Okay.");

                System.out.print("准备阶段应答不全进行回滚 XA branch (" +
                        Byte.toString(shipping_Xid.getGlobalTransactionId()[0]) + ", " +
                        Byte.toString(shipping_Xid.getBranchQualifier()[0]) + "): ");
                shippingXAResource.rollback(shipping_Xid);
                System.out.println("Okay.");
            }
        } catch (Exception e) {
            if (e instanceof XAException) {
                xaERR((XAException) e);
            } else if (e instanceof SQLException) {
                sqlERR((SQLException) e);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * sql错误
     *
     * @param exception
     */
    private void sqlERR(SQLException exception) {
        System.err.println("FAILED.");
        while (exception != null) {
            System.err.println("==> SQL异常");
            System.err.println("--> SQLCODE : " + exception.getErrorCode());
            System.err.println("--> SQLSTATE: " + exception.getSQLState());
            System.err.println("--> Message : " + exception.getMessage());
            exception.printStackTrace();
            exception = exception.getNextException();
        }
    }

    /**
     * XA错误
     *
     * @param exception
     */
    private void xaERR(XAException exception) {
        System.err.println("FAILED.");
        System.err.println("==> XA 异常");
        System.err.println("--> Cause  : " + exception.getCause());
        System.err.println("--> Message: " + exception.getMessage());
        //exception.printStackTrace();
    }

}
