/*
 * Copyright (c) 博学谷 http://www.boxuegu.com
 *
 */

package com.boxuegu.arch.demo.distranction.twophasecommit.V2;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Properties;

/**
 * @author Jam Fang  https://www.jianshu.com/u/0977ede560d4
 * @version 创建时间：2019/4/14 16:08
 */
@Configuration
public class BaseDataSourceConfig {
    @Bean(name = "orderXADataSource")
    @Autowired
    public DruidXADataSource orderXADataSource(Environment env) {
        System.out.print("创建订单数据源");
        Properties props = build(env, "spring.datasource.druid.order.");
        DruidXADataSource xaDataSource = initDataSource(props);
        System.out.println("Okay.");
        return xaDataSource;
    }

    @Bean(name = "shippingXADataSource")
    @Autowired
    public DruidXADataSource shippingDataSource(Environment env) {
        System.out.print("创建物流数据源: ");
        Properties props = build(env, "spring.datasource.druid.shipping.");
        DruidXADataSource xaDataSource = initDataSource(props);
        System.out.println("Okay.");
        return xaDataSource;
    }

    private DruidXADataSource initDataSource(Properties props) {
        DruidXADataSource xaDataSource = new DruidXADataSource();
        xaDataSource.setUrl(props.getProperty("url"));
        xaDataSource.setUsername(props.getProperty("username"));
        xaDataSource.setPassword(props.getProperty("password"));
        return xaDataSource;
    }

    private Properties build(Environment env, String prefix) {
        Properties prop = new Properties();
        prop.put("url", env.getProperty(prefix + "url"));
        prop.put("username", env.getProperty(prefix + "username"));
        prop.put("password", env.getProperty(prefix + "password"));
        prop.put("dbtype", env.getProperty(prefix + "dbtype"));
        prop.put("type", env.getProperty(prefix + "type"));
        return prop;
    }
}