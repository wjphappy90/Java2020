/*
 * Copyright (c) 博学谷 http://www.boxuegu.com
 *
 */

package com.boxuegu.arch.demo.distransaction.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;
import java.util.Properties;

/**
 * @author Jam Fang  https://www.jianshu.com/u/0977ede560d4
 * @version 创建时间：2019/4/16 17:09
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
public class TransactionManagerConfig {
    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;
    }

    @Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
    public UserTransactionManager atomikosTransactionManager() throws Throwable {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    @Bean(name = "transactionManager")
    @DependsOn({"userTransaction", "atomikosTransactionManager"})
    public JtaTransactionManager transactionManager() throws Throwable {
        UserTransaction userTransaction = userTransaction();

        JtaTransactionManager manager = new JtaTransactionManager(userTransaction, atomikosTransactionManager());
        return manager;
    }

    //@Bean(name = "transactionInterceptor")
    public TransactionInterceptor transactionInterceptor(PlatformTransactionManager platformTransactionManager) {
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        // 事物管理器
        transactionInterceptor.setTransactionManager(platformTransactionManager);
        Properties transactionAttributes = new Properties();

        // test
        transactionAttributes.setProperty("test*", "PROPAGATION_REQUIRED,-Throwable");

        // 新增
        transactionAttributes.setProperty("insert*", "PROPAGATION_REQUIRED,-Throwable");
        // 修改

        transactionAttributes.setProperty("update*", "PROPAGATION_REQUIRED,-Throwable");
        // 删除
        transactionAttributes.setProperty("delete*", "PROPAGATION_REQUIRED,-Throwable");
        // 查询
        transactionAttributes.setProperty("select*", "PROPAGATION_REQUIRED,-Throwable,readOnly");

        transactionInterceptor.setTransactionAttributes(transactionAttributes);
        return transactionInterceptor;

    }

    // 代理到ServiceImpl的Bean
    @Bean
    public BeanNameAutoProxyCreator transactionAutoProxy() {
        BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
        transactionAutoProxy.setProxyTargetClass(true);
        transactionAutoProxy.setBeanNames("*ServiceImpl");
        transactionAutoProxy.setInterceptorNames("transactionInterceptor");
        return transactionAutoProxy;
    }
}