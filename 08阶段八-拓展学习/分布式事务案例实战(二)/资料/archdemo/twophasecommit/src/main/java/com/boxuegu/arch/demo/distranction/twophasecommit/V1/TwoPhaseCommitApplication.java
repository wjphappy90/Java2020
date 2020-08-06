/*
 * Copyright (c) 博学谷 http://www.boxuegu.com
 *
 */

package com.boxuegu.arch.demo.distranction.twophasecommit.V1;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlXid;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author Jam Fang  https://www.jianshu.com/u/0977ede560d4
 * @version 创建时间：2019/4/14 13:58
 */
public class TwoPhaseCommitApplication {
    public static void main(String args[]) {
        try {
            new TwoPhaseCommitApplication().multiDataSourceTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void multiDataSourceTest() throws Exception {
        String propertyfile = "/app.properties";
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream(propertyfile));
        //初始化数据源
        DruidXADataSource xaDataSource_1 = initXADataSource(props, "db1.");
        //初始化XA连接
        XAConnection xaConnection_1 = xaDataSource_1.getXAConnection();
        //初始化XA资源
        XAResource xaResource_1 = xaConnection_1.getXAResource();
        //获得数据库连接
        Connection connection_1 = xaConnection_1.getConnection();
        connection_1.setAutoCommit(false);
        //创建XID
        Xid xid_1 = new MysqlXid("globalid".getBytes(), "branch-1".getBytes(), 0);
        //关联事务start end
        xaResource_1.start(xid_1, XAResource.TMNOFLAGS);
        Statement stmt = connection_1.createStatement();
        String sql_1 = "INSERT INTO `order`(orderid,amount,product) values('00002','3000.00','ardemo1-苹果笔记本');";
        stmt.executeUpdate(sql_1);
        xaResource_1.end(xid_1, XAResource.TMSUCCESS);


        DruidXADataSource xaDataSource_2 = initXADataSource(props, "db2.");
        XAConnection xaConnection_2 = xaDataSource_2.getXAConnection();
        XAResource xaResource_2 = xaConnection_2.getXAResource();
        Connection connection_2 = xaConnection_2.getConnection();
        connection_2.setAutoCommit(false);
        Xid xid_2 = new MysqlXid("globalid".getBytes(), "branch-2".getBytes(), 0);
        xaResource_2.start(xid_2, XAResource.TMNOFLAGS);
        Statement stmt2 = connection_2.createStatement();
        String sql_2 = "update shipping set address='archdemo2-北京黄浦江畔123' where id=1;";
        stmt2.executeUpdate(sql_2);
        xaResource_2.end(xid_2, XAResource.TMSUCCESS);
        try {
            //事务准备
            int result_1 = xaResource_1.prepare(xid_1);
            int result_2 = xaResource_2.prepare(xid_2);
            //XA事务 准备阶段
            int i = 1 / 0;
            if (result_1 == XAResource.XA_OK &&
                    result_2 == XAResource.XA_OK) {
                //都返回OK的话，进行提交阶段
                xaResource_1.commit(xid_1, false);
                xaResource_2.commit(xid_2, false);
            } else {
                //回滚事务
                xaResource_1.rollback(xid_1);
                xaResource_2.rollback(xid_2);
            }
        } catch (Exception e) {
            System.out.println("回滚事务");
            //回滚事务
            try {
                xaResource_1.rollback(xid_1);
            } catch (Exception e1) {

            }
            xaResource_2.rollback(xid_2);
        }
    }

    DruidXADataSource initXADataSource(Properties props, String prefix) {
        DruidXADataSource xaDataSource = new DruidXADataSource();
        xaDataSource.setDbType(props.getProperty(prefix + "dbtype"));
        xaDataSource.setUrl(props.getProperty(prefix + "url"));
        xaDataSource.setUsername(props.getProperty(prefix + "username"));
        xaDataSource.setPassword(props.getProperty(prefix + "password"));
        return xaDataSource;
    }

}