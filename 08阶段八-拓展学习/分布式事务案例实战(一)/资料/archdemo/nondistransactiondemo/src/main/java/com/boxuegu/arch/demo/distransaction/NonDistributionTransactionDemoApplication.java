/*
 * Copyright (c) 博学谷 http://www.boxuegu.com
 *
 */

package com.boxuegu.arch.demo.distransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Jam Fang  https://www.jianshu.com/u/0977ede560d4
 * @version 创建时间：2019/4/16 16:54
 */

@Configuration
@ConditionalOnMissingBean(PlatformTransactionManager.class)
@EnableTransactionManagement
@SpringBootApplication
public class NonDistributionTransactionDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(NonDistributionTransactionDemoApplication.class, args);
    }

}
