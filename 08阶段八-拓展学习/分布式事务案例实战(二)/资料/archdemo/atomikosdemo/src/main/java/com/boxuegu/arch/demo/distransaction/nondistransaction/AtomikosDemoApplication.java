/*
 * Copyright (c) 博学谷 http://www.boxuegu.com
 *
 */

package com.boxuegu.arch.demo.distransaction.nondistransaction;

import com.boxuegu.arch.demo.distransaction.config.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Jam Fang  https://www.jianshu.com/u/0977ede560d4
 * @version 创建时间：2019/4/16 16:54
 */

@EnableAutoConfiguration
@ComponentScan("com.boxuegu.arch.demo.distransaction")
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@Import({DataSourceConfig.class})
public class AtomikosDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(AtomikosDemoApplication.class, args);
    }

}
